package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.TaskCheckService;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.TaskHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.TaskService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 16:46
 */
@Component
@Slf4j
public class TaskHandlerImpl implements TaskHandler {
    private TaskService taskService;
    private TaskCheckService taskCheckService;





    @Autowired
    public void setTaskCheckService(TaskCheckService taskCheckService) {
        this.taskCheckService = taskCheckService;
    }
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResultVo queryAllTasks(PageBean<TaskVo> pageBean, TaskVo taskVo) {
        ResultVo resultVo = taskCheckService.checkQueryQueryAllTaskParams(pageBean,taskVo);
        if (resultVo == null ||resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo
        return null;
    }

    @Override
    public ResultVo queryTaskLogs(PageBean<TaskLogVo> pageBean, TaskLogVo taskLogVo) {
        ResultVo resultVo = taskCheckService.checkQueryQueryTaskLogsParams(pageBean,taskLogVo);
        if (resultVo == null ||resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo
        return null;
    }

    @Override
    public ResultVo createTask(TaskCreateVo taskCreateVo) {
        ResultVo resultVo = taskCheckService.checkTaskCreateParams(taskCreateVo);
        if (resultVo == null ||resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo
        return null;
    }

    @Override
    public ResultVo updateTask(TaskUpdateVo taskUpdateVo) {
        ResultVo resultVo = taskCheckService.checkTaskUpdateParams(taskUpdateVo);
        if (resultVo == null ||resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo
        return null;
    }

    @Override
    public ResultVo updateTaskStatus(Integer taskId, Integer status) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo executeTask(Integer taskId) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo deleteTask(Integer taskId) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
