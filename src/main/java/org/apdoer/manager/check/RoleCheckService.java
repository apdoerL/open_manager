package org.apdoer.manager.check;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.RoleCreateVo;
import org.apdoer.manager.model.vo.RoleUpdateVo;
import org.apdoer.manager.model.vo.RoleVo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:12
 */
public interface RoleCheckService {
    /**
     * 校验查询角色列表参数
     * @param pageBean
     * @param roleVo
     * @return
     */
    ResultVo checkQueryRoleListParam(PageBean<RoleVo> pageBean, RoleVo roleVo);

    /**
     * 校验创建角色参数
     * @param roleCreateVo
     * @return
     */
    ResultVo checkRoleCreateParam(RoleCreateVo roleCreateVo);

    ResultVo checkRoleUpdateParam(RoleUpdateVo roleUpdateVo);
}
