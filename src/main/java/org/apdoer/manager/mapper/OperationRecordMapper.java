package org.apdoer.manager.mapper;

import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.model.vo.OperationRecordVo;

import java.util.List;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/20 18:28
 */
public interface OperationRecordMapper extends BaseMapper<RecordPo> {

    List<OperationRecordVo> queryRecords(OperationRecordVo operationRecordVo);
}
