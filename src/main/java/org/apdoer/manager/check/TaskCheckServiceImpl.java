package org.apdoer.manager.check;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/24 17:34
 */
@Component
@Slf4j
public class TaskCheckServiceImpl implements TaskCheckService {
    private TaskService taskService;


    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResultVo checkQueryQueryAllTaskParams(PageBean<TaskVo> pageBean, TaskVo taskVo) {
        // todo 分页数据

        //todo
        return null;
    }

    @Override
    public ResultVo checkQueryQueryTaskLogsParams(PageBean<TaskLogVo> pageBean, TaskLogVo taskLogVo) {
        // todo 分页数据

        //todo
        return null;
    }

    @Override
    public ResultVo checkTaskCreateParams(TaskCreateVo taskCreateVo) {
        // todo 校验taskName重名
        // todo 校验beanName重名

        // todo cron

        //todo
        return null;
    }

    @Override
    public ResultVo checkTaskUpdateParams(TaskUpdateVo taskUpdateVo) {
        // todo 校验taskName重名
        // todo 校验beanName重名

        //todo
        return null;
    }
}
