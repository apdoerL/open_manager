package org.apdoer.manager.utils;

import cn.hutool.json.JSONObject;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.exception.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 获取当前登录的用户
 * @author apdoer
 * @date 2019-01-17
 */
@Component
public class SecurityUtil {

    public static UserDetails getUserDetails() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (UserDetails) principal;
        } catch (Exception e) {
            throw new UnAuthorizedException(ExceptionCodeEnum.UNAUTHORIZED_USERS);
        }
    }

    /**
     * 获取系统用户名称
     * @return 系统用户名称
     */
    public static String getUsername(){
        Object obj = getUserDetails();
        JSONObject json = new JSONObject(obj);
        return json.get("username", String.class);
    }

    /**
     * 获取系统用户id
     * @return 系统用户id
     */
    public static Long getUserId(){
        Object obj = getUserDetails();
        JSONObject json = new JSONObject(obj);
        return json.get("id", Long.class);
    }
}
