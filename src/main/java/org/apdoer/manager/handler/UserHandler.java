package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/19 17:43
 */
public interface UserHandler {
    /**
     * 查询用户列表
     * @param pageBean pageBean
     * @param userVo uservo
     * @return list
     */
    ResultVo queryUsers(PageBean<UserVo> pageBean, UserVo userVo);

    /**
     * 创建后台用户
     * @param userCreateVo userCreatevo
     * @return result
     */
    ResultVo createUser(UserCreateVo userCreateVo);

    /**
     * 更新后台用户信息
     * @param userUpdateVo
     * @return
     */
    ResultVo updateUser(UserUpdateVo userUpdateVo);

    /**
     * 删除后台用户
     * @param userVo
     * @return
     */
    ResultVo deletUser(UserVo userVo);

    /**
     * 更新后台用户密码
     * @param userPwdUpdateVo
     * @return
     */
    ResultVo updateUserPwd(UserPwdUpdateVo userPwdUpdateVo);

    /**
     * 更新头像
     * @param file
     * @return
     */
    ResultVo updateAvator(MultipartFile file);
}
