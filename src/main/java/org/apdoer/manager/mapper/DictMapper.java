package org.apdoer.manager.mapper;

import org.apdoer.manager.model.dto.DictDTO;
import org.apdoer.manager.model.pojo.DictPo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author apdoer
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends EntityMapper<DictDTO, DictPo> {

}