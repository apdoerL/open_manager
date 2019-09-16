package org.apdoer.manager.mapper;

import org.apdoer.manager.model.dto.UserDTO;
import org.apdoer.manager.model.pojo.BizUserPo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author apdoer
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, BizUserPo> {

}
