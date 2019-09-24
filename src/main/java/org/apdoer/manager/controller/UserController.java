package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.UserHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.utils.GoogleAuthUtils;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author apdoer
 * @date 2018-11-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserHandler userHandler;

    @Autowired
    public void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }





    @GetMapping("/users")
    @SystemControllerLog("查询后台用户列表")
    public ResultVo queryUsers(PageBean<UserVo>pageBean, UserVo userVo){
        return userHandler.queryUsers(pageBean,userVo);
    }



    @PostMapping("/user")
    @SystemControllerLog("创建后台用户")
    public ResultVo createUser(@Validated @RequestBody UserCreateVo userCreateVo){
        return userHandler.createUser(userCreateVo);
    }


    @PutMapping("/user")
    @SystemControllerLog("更新后台用户信息")
    public ResultVo updateUser(@RequestBody @Validated UserUpdateVo userUpdateVo){
        return userHandler.updateUser(userUpdateVo);
    }



    @DeleteMapping("/user/{userId}")
    @SystemControllerLog("删除后台用户")
    public ResultVo deleteUser( @PathVariable("userId") Long userId){
        return userHandler.deletUser(userId);
    }


    @PutMapping("/userPwd")
    @SystemControllerLog("修改后台用户密码")
    public ResultVo updatePwd(@Validated @RequestBody UserPwdUpdateVo userPwdUpdateVo){
        return userHandler.updateUserPwd(userPwdUpdateVo);
    }



    @PutMapping("/avator")
    @SystemControllerLog("更换头像")
    public ResultVo updateAvator(@RequestParam MultipartFile file){
        return userHandler.updateAvator(file);
    }

    @GetMapping("/googleCode")
    @SystemControllerLog("获取谷歌验证码")
    public ResultVo getGoogleCode(){
        return ResultVoBuildUtils.buildSuccessResultVo(GoogleAuthUtils.getKey());
    }

}
