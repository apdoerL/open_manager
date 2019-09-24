package org.apdoer.manager.check;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;

/**
 * @author apdoer
 */
public interface SystemLogCheckService {
    /**
     * 校验查询操作日志参数
     * @param pageBean
     * @param operationRecordVo
     * @return
     */
    ResultVo checkQueryRecordsParam(PageBean<OperationRecordVo> pageBean, OperationRecordVo operationRecordVo);
}
