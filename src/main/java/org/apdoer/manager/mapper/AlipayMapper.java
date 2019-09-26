package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Select;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.AlipayPo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/26 11:07
 */
public interface AlipayMapper extends BaseMapper<AlipayPo> {
    @Select("select * from core_param_alipay where id = 1 ")
    AlipayPo queryById();

}
