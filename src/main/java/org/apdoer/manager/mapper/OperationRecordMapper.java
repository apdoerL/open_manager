package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Select;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.model.vo.OperationRecordVo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/20 18:28
 */
public interface OperationRecordMapper extends BaseMapper<RecordPo> {

    /**
     * 查询操作记录
     * @param operationRecordVo vo
     * @return list
     */
    List<OperationRecordVo> queryRecords(OperationRecordVo operationRecordVo);

    /**
     * 这里提供一个注解方式的查询 实际生产不推荐,不好维护,而且复杂的sql难写,但是达到的效果是一样的,如果有需要复杂查询实现的话,可以提issue,我会在后期迭代出来
     * 当日访问ip
     * @param toString start
     * @param toString1 end
     * @return count
     */
    @Select("select count(*) FROM (select ip FROM biz_record where create_time between ${0} and ${1} GROUP BY ip) as result")
    Long queryIpCountsByduration( String toString,  String toString1);
}
