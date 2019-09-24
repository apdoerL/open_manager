package org.apdoer.manager.check.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.UserCheckService;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/19 17:51
 */
@Component
@Slf4j
public class UserCheckServiceImpl implements UserCheckService {

    @Override
    public ResultVo checkQueryUserParam(PageBean<UserVo> pageBean, UserVo userVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkCreateUserParam(UserCreateVo userCreateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkUpdateUserParam(UserUpdateVo userUpdateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkUpdateUserPasswordParam(UserPwdUpdateVo userPwdUpdateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
