package org.apdoer.manager.mapper;

import org.apdoer.manager.model.dto.LogErrorDTO;
import org.apdoer.manager.model.pojo.LogPo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author apdoer
 * @date 2019-5-22
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends EntityMapper<LogErrorDTO, LogPo> {

}