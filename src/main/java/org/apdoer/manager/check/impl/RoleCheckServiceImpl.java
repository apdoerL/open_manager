package org.apdoer.manager.check.impl;

import org.apdoer.manager.check.RoleCheckService;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.RoleCreateVo;
import org.apdoer.manager.model.vo.RoleVo;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:12
 */
@Component
public class RoleCheckServiceImpl implements RoleCheckService {

    @Override
    public ResultVo checkQueryRoleListParam(PageBean<RoleVo> pageBean, RoleVo roleVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkRoleCreateParam(RoleCreateVo roleCreateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
