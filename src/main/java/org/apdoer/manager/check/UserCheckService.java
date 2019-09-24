package org.apdoer.manager.check;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/19 17:50
 */
public interface UserCheckService {
    /**
     * 校验查询用户列表参数
     * @param pageBean
     * @param userVo
     * @return
     */
    ResultVo checkQueryUserParam(PageBean<UserVo> pageBean, UserVo userVo);

    /**
     * 校验创建后台用户参数
     * @param userCreateVo
     * @return
     */
    ResultVo checkCreateUserParam(UserCreateVo userCreateVo);

    /**
     * 校验更新后台用户参数
     * @param userUpdateVo
     * @return
     */
    ResultVo checkUpdateUserParam(UserUpdateVo userUpdateVo);

    /**
     * 校验修改密码参数
     * @param userPwdUpdateVo
     * @return
     */
    ResultVo checkUpdateUserPasswordParam(UserPwdUpdateVo userPwdUpdateVo);
}
