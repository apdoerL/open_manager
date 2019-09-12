package org.apdoer.manager.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RequiresPermissionsInterceptorService implements HandlerInterceptor {

	@Autowired
	private SecurityUtils securityUtils;
	
	@Autowired
	private UserService userService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (this.hasPermission(handler)) {
			return true;
		}
		response.sendError(org.springframework.http.HttpStatus.FORBIDDEN.value(), "无权限");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private boolean hasPermission(Object handler) {
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			return hasRequiresPermissionHandle(handlerMethod);
		}
		return true;
	}

	private boolean hasRequiresPermissionHandle(HandlerMethod handlerMethod) {
		// 获取方法上的注解
		RequiresPermissions requiredPermission = handlerMethod.getMethod().getAnnotation(RequiresPermissions.class);
		if (null == requiredPermission) {
			requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiresPermissions.class);
		}

		if (null != requiredPermission && null != requiredPermission.value() //设置了权限控制
				&& requiredPermission.value().length > 0) {      
			Logical logical = requiredPermission.logical();       

			List<String> permissionList = this.getUserPermissionList();
			if (null == permissionList || permissionList.size() == 0) {       //个人无权限
				return false;
			} else {
				if (logical == Logical.AND) {
					for (String per : requiredPermission.value()) {
						if (!permissionList.contains(per)) {
							return false;               //无当前权限
						}
					}
					return true;   //有权限
				} else {
					for (String per : requiredPermission.value()) {
						if (permissionList.contains(per)) {
							return true;
						}
					}
					return false;
				}
			}
		} else {   //未设置权限控制
			return true;
		}
	}
	
	private List<String> getUserPermissionList() {
		Object userIdObject = this.securityUtils.getCurrentUserId();
		if (null == userIdObject) {
			return null;
		} else {
			List<PermissionPo> permissionPoList = this.userService.getPermissionList(new Integer(userIdObject.toString()));
			if (null != permissionPoList && permissionPoList.size() > 0) {
				List<String> list = new ArrayList<>();
				for (PermissionPo permission : permissionPoList) {
					list.add(permission.getValue());
				}
				return list;
			} else {
				return null;
			}
		}
	}
}
