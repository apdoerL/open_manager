package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.UserHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
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
    @Autowired
    private UserHandler userHandler;









    @GetMapping("/test")
    public ResultVo test(String value){
        return ResultVoBuildUtils.buildSuccessResultVo(value);
    }


    /**
     * 查询后台用户列表
     * @param pageBean
     * @param userVo
     * @return
     */
    @GetMapping("/users")
    @SystemControllerLog("查询后台用户列表")
    public ResultVo queryUsers(PageBean<UserVo>pageBean, UserVo userVo){
        return userHandler.queryUsers(pageBean,userVo);
    }


    /**
     * 创建后台用户
     * @param userCreateVo
     * @return
     */
    @PostMapping("/user")
    @SystemControllerLog("创建后台用户")
    public ResultVo createUser(@Validated @RequestBody UserCreateVo userCreateVo){
        return userHandler.createUser(userCreateVo);
    }

    /**
     * 更新后台用户信息
     * @param userUpdateVo
     * @return
     */
    @PutMapping("/user")
    @SystemControllerLog("更新后台用户信息")
    public ResultVo updateUser(@RequestBody @Validated UserUpdateVo userUpdateVo){
        return userHandler.updateUser(userUpdateVo);
    }


    /**
     * 删除后台用户
     * @param userId
     * @return
     */
    @DeleteMapping("/user/{userId}")
    @SystemControllerLog("删除后台用户")
    public ResultVo deleteUser( @PathVariable("userId") Long userId){
        return userHandler.deletUser(userId);
    }


    /**
     * 修改后台用户密码
     * @param userPwdUpdateVo
     * @return
     */
    @PutMapping("/userPwd")
    @SystemControllerLog("修改后台用户密码")
    public ResultVo updatePwd(@Validated @RequestBody UserPwdUpdateVo userPwdUpdateVo){
        return userHandler.updateUserPwd(userPwdUpdateVo);
    }


    /**
     * 更换头像
     * @param file
     * @return
     */
    @PutMapping("/avator")
    @SystemControllerLog("更换头像")
    public ResultVo updateAvator(@RequestParam MultipartFile file){
        return userHandler.updateAvator(file);
    }

}
