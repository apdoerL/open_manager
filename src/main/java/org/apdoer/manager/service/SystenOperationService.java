package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.model.vo.OperationRecordVo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:56
 */
public interface SystenOperationService {
    /**
     * 新增日志记录
     * @param recordPo recordPo
     */
    void insertRecord(RecordPo recordPo);

    /**
     * 查询所有的日志记录
     * @param operationRecordVo vo
     * @return list
     */
    List<OperationRecordVo> queryAllRecords(OperationRecordVo operationRecordVo);
}
