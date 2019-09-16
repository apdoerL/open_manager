package org.apdoer.manager.mapper;

import org.apdoer.manager.model.dto.MenuDTO;
import org.apdoer.manager.model.pojo.MenuPo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author apdoer
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, MenuPo> {

}
