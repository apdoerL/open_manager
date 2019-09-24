package org.apdoer.manager.handler.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.tools.web.Webserver;
import org.apdoer.manager.check.SystemLogCheckService;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.SystemOperationHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.service.SystenOperationService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:54
 */
@Service
@Slf4j
public class SystemOperationHandlerImpl implements SystemOperationHandler {
    private SystenOperationService operationService;
    private SystemLogCheckService systemLogCheckService;

    @Autowired
    public void setSystemLogCheckService(SystemLogCheckService systemLogCheckService) {
        this.systemLogCheckService = systemLogCheckService;
    }

    @Autowired
    public void setOperationService(SystenOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResultVo queryAllRecord(PageBean<OperationRecordVo> pageBean, OperationRecordVo operationRecordVo) {
        ResultVo resultVo = systemLogCheckService.checkQueryRecordsParam(pageBean,operationRecordVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<OperationRecordVo> vos = operationService.queryAllRecords(operationRecordVo);
        PageInfo<OperationRecordVo>pageInfo = new PageInfo<>(vos);
        return ResultVoBuildUtils.buildSuccessResultVo(pageInfo);
    }
}
