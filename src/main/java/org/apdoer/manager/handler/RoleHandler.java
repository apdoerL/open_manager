package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.RoleCreateVo;
import org.apdoer.manager.model.vo.RoleUpdateVo;
import org.apdoer.manager.model.vo.RoleVo;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/19 19:05
 */
public interface RoleHandler {
    /**
     * 查询角色列表
     * @param pageBean
     * @param roleVo
     * @return
     */
    ResultVo queryRoleList(PageBean<RoleVo> pageBean, RoleVo roleVo);

    /**
     * 查询所有可用角色
     * @return
     */
    ResultVo queryAllCreatedRole();

    /**
     * 创建角色
     * @param roleCreateVo
     * @return
     */
    ResultVo createRole(RoleCreateVo roleCreateVo);

    /**
     * 更新角色
     * @param roleUpdateVo
     * @return
     */
    ResultVo updateRole(RoleUpdateVo roleUpdateVo);
}
