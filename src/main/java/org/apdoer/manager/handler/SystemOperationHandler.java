package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.model.vo.ResultVo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:54
 */
public interface SystemOperationHandler {
    /**
     * 查询所有的操作记录
     * 之后配置定时任务,定时清除操作数据
     * @param pageBean
     * @param operationRecordVo
     * @return
     */
    ResultVo queryAllRecord(PageBean<OperationRecordVo> pageBean, OperationRecordVo operationRecordVo);
}
