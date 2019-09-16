package org.apdoer.manager.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apdoer.manager.annotations.RequiresPermissions;
import org.apdoer.manager.enums.LogicalEnum;
import org.apdoer.manager.service.UserService;
import org.apdoer.manager.spring.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author apdoer
 */
@Component
public class RequiresPermissionsInterceptorService implements HandlerInterceptor {
    private SecurityUtils securityUtils;
    private UserService userService;







    @Autowired
    public void setSecurityUtils(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }





	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (this.hasPermission(handler)) {
			return true;
		}
		response.sendError(org.springframework.http.HttpStatus.FORBIDDEN.value(), "无权限");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
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

		if (null != requiredPermission && requiredPermission.value().length > 0) {
            //设置了权限控制
			LogicalEnum logical = requiredPermission.logical();

			List<String> permissionList = this.getUserPermissionList();
			if (null == permissionList || permissionList.size() == 0) {
                //个人无权限
				return false;
			} else {
				if (logical == LogicalEnum.AND) {
					for (String per : requiredPermission.value()) {
						if (!permissionList.contains(per)) {
                            //无当前权限
							return false;
						}
					}
                    //有权限
					return true;
				} else {
					for (String per : requiredPermission.value()) {
						if (permissionList.contains(per)) {
							return true;
						}
					}
					return false;
				}
			}
		} else {
            //未设置权限控制
			return true;
		}
	}

	private List<String> getUserPermissionList() {
		Object userIdObject = this.securityUtils.getCurrentUserId();
		if (null == userIdObject) {
			return null;
		} else {
			List<String> permissionPoList = this.userService.getPermissionList(new Integer(userIdObject.toString()));
			if (!CollectionUtils.isEmpty(permissionPoList)) {
                return new ArrayList<>(permissionPoList);
			} else {
				return null;
			}
		}
	}
}
