package org.apdoer.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author apdoer
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;


    @Value("${manager.mock}")
    private boolean mock;
    /**
     * 登录授权
     * @param authorizationUser
     * @return
     */
    @SystemControllerLog("用户登录")
    @PostMapping(value = "${jwt.auth.path}")
    public ResultVo login(@Validated @RequestBody AuthorizationUserVo authorizationUser){
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());
        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
            throw new AccountExpiredException("密码错误");
        }
        if(!jwtUser.isEnabled()){
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }
        if (!mock) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(authorizationUser.getGoogleCode())
                    && !GoogleAuthUtils.isCodeValid(jwtUser.getGoogleCode(), Integer.parseInt(authorizationUser.getGoogleCode()))) {
                return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.GOOGLE_AUTHENTICATION_CODE_ERROR.getCode(), ExceptionCodeEnum.GOOGLE_AUTHENTICATION_CODE_ERROR.getValue());
            }
        }
        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);
        // 返回 token
        return ResultVoBuildUtils.buildSuccessResultVo(new AuthenticationVo(token,jwtUser));
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResultVo getUserInfo(){
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(SecurityUtil.getUsername());
        return ResultVoBuildUtils.buildSuccessResultVo(jwtUser);
    }

}
