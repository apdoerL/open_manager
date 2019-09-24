package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 16:46
 */
public interface TaskHandler {
    /**
     * 查询所有的定时任务
     * @param pageBean page
     * @param taskVo vo
     * @return result
     */
    ResultVo queryAllTasks(PageBean<TaskVo> pageBean, TaskVo taskVo);

    /**
     * 查询所有的定时任务执行日志
     * @param pageBean page
     * @param taskLogVo vo
     * @return result
     */
    ResultVo queryTaskLogs(PageBean<TaskLogVo> pageBean, TaskLogVo taskLogVo);

    /**
     * 创建定时任务
     * @param taskCreateVo taskCreateVo
     * @return result
     */
    ResultVo createTask(TaskCreateVo taskCreateVo);

    /**
     * 编辑定时任务
     * @param taskUpdateVo taskUpdateVo
     * @return result
     */
    ResultVo updateTask(TaskUpdateVo taskUpdateVo);

    /**
     * 更新定时任务状态
     * @param taskId taskId
     * @param  status status
     * @return Result
     */
    ResultVo updateTaskStatus(Integer taskId, Integer status);

    /**
     * 执行定时任务
     * @param  taskId taskId
     * @return Resul
     */
    ResultVo executeTask(Integer taskId);

    /**
     * 删除定时任务
     * @param taskId taskId
     * @return result
     */
    ResultVo deleteTask(Integer taskId);
}
