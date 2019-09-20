package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.mapper.OperationRecordMapper;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.service.SystenOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/20 18:56
 */
@Service
@Slf4j
public class SystenOperationServiceImpl implements SystenOperationService {
    private OperationRecordMapper recordMapper;

    @Autowired
    public void setRecordMapper(OperationRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public void insertRecord(RecordPo recordPo) {

    }
}
