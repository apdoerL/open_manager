package org.apdoer.manager.check;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 17:34
 */
public interface TaskCheckService {
    /**
     * 校验查询定时任务参数
     * @param pageBean page
     * @param taskVo vo
     * @return re
     */
    ResultVo checkQueryQueryAllTaskParams(PageBean<TaskVo> pageBean, TaskVo taskVo);

    /**
     * 校验查询定时任务日志参数
     * @param pageBean page
     * @param taskLogVo vo
     * @return re
     */
    ResultVo checkQueryQueryTaskLogsParams(PageBean<TaskLogVo> pageBean, TaskLogVo taskLogVo);

    /**
     * 校验创建定时任务参数
     * @param taskCreateVo vo
     * @return re
     */
    ResultVo checkTaskCreateParams(TaskCreateVo taskCreateVo);

    /**
     * 校验更新定时任务参数
     * @param taskUpdateVo vo
     * @return r
     */
    ResultVo checkTaskUpdateParams(TaskUpdateVo taskUpdateVo);
}
