package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.SystemOperationHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/20 17:46
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private SystemOperationHandler systemOperationHandler;

    @Autowired
    public void setSystemOperationHandler(SystemOperationHandler systemOperationHandler) {
        this.systemOperationHandler = systemOperationHandler;
    }

    @GetMapping("/records")
    @SystemControllerLog("/查询操作日志")
    public ResultVo queryOperationRecord(PageBean<OperationRecordVo>pageBean,OperationRecordVo operationRecordVo){
        return systemOperationHandler.queryAllRecord(pageBean,operationRecordVo);
    }
}
