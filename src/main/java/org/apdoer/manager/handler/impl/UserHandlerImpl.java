package org.apdoer.manager.handler.impl;

import org.apdoer.manager.check.UserCheckService;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.UserHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.UserService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 17:43
 */
@Component
public class UserHandlerImpl implements UserHandler {
    private UserService userService;
    private UserCheckService userCheckService;





    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserCheckService(UserCheckService userCheckService) {
        this.userCheckService = userCheckService;
    }



    @Override
    public ResultVo queryUsers(PageBean<UserVo> pageBean, UserVo userVo) {
        ResultVo resultVo = userCheckService.checkQueryUserParam(pageBean,userVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo createUser(UserCreateVo userCreateVo) {
        ResultVo resultVo = userCheckService.checkCreateUserParam(userCreateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo updateUser(UserUpdateVo userUpdateVo) {
        ResultVo resultVo = userCheckService.checkUpdateUserParam(userUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo deletUser(Long userId) {
        // todo
        return null;
    }

    @Override
    public ResultVo updateUserPwd(UserPwdUpdateVo userPwdUpdateVo) {
        ResultVo resultVo = userCheckService.checkUpdateUserPasswordParam(userPwdUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo updateAvator(MultipartFile file) {
        //校验上传文件后缀,大小...

        //上传头像

        //更新数据库

        // todo
        return null;
    }
}
