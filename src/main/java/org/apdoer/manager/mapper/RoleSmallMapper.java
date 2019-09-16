package org.apdoer.manager.mapper;

import org.apdoer.manager.model.dto.RoleSmallDTO;
import org.apdoer.manager.model.pojo.RolePo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author apdoer
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends EntityMapper<RoleSmallDTO, RolePo> {

}
