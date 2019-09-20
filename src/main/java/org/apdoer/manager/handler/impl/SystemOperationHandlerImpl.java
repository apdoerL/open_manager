package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.SystemOperationHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.service.SystenOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:54
 */
@Service
@Slf4j
public class SystemOperationHandlerImpl implements SystemOperationHandler {
    private SystenOperationService operationService;

    @Autowired
    public void setOperationService(SystenOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResultVo queryAllRecord(PageBean<OperationRecordVo> pageBean, OperationRecordVo operationRecordVo) {
        return null;
    }
}
