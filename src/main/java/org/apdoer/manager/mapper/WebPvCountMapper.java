package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.WebPvCountPo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/23 16:21
 */
public interface WebPvCountMapper extends BaseMapper<WebPvCountPo> {


    WebPvCountPo queryByDate(String date);

    List<WebPvCountPo> queryRecordsByDuration(@Param("start") String start, String end);
}
