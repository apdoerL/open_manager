package org.apdoer.manager.check.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.SystemLogCheckService;
import org.apdoer.manager.constants.ManagerConstant;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.stereotype.Service;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class SystemLogCheckServiceImpl implements SystemLogCheckService {
    @Override
    public ResultVo checkQueryRecordsParam(PageBean<OperationRecordVo> pageBean, OperationRecordVo operationRecordVo) {
        if (pageBean == null || pageBean.getPageSize() > ManagerConstant.MAX_PAGE_SIZE){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        //todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
