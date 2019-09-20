package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.RecordPo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:56
 */
public interface SystenOperationService {
    /**
     * 新增日志记录
     * @param recordPo
     */
    void insertRecord(RecordPo recordPo);
}
