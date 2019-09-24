package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.TaskHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskHandler taskHandler;



    @Autowired
    public void setTaskHandler(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }


    @GetMapping("/tasks")
    @SystemControllerLog("查询所有定时任务")
    public ResultVo queryAllTasks(PageBean<TaskVo> pageBean, TaskVo taskVo) {
        return taskHandler.queryAllTasks(pageBean, taskVo);
    }

    @GetMapping("/taskLogs")
    @SystemControllerLog("查询定时任务执行日志")
    public ResultVo querytaskLogs(PageBean<TaskLogVo> pageBean, TaskLogVo taskLogVo) {
        return taskHandler.queryTaskLogs(pageBean, taskLogVo);
    }

    @PostMapping("/task")
    @SystemControllerLog("创建定时任务")
    public ResultVo createTask(@RequestBody @Validated TaskCreateVo taskCreateVo) {
        return taskHandler.createTask(taskCreateVo);
    }

    @PutMapping("/task")
    @SystemControllerLog("编辑定时任务")
    public ResultVo updateTask(@RequestBody @Validated TaskUpdateVo taskUpdateVo) {
        return taskHandler.updateTask(taskUpdateVo);
    }

    @PutMapping("/taskStatus/{taskId}/{status}")
    @SystemControllerLog("更新定时任务状态")
    public ResultVo updateTaskStatus(@PathVariable("taskId") Integer taskId, @PathVariable("status") Integer status) {
        return taskHandler.updateTaskStatus(taskId, status);
    }

    @PostMapping("/execution/{taskId}")
    @SystemControllerLog("执行定时任务")
    public ResultVo executeTask(@PathVariable("taskId") Integer taskId) {
        return taskHandler.executeTask(taskId);
    }

    @DeleteMapping("/task/{taskId}")
    @SystemControllerLog("删除定时任务")
    public ResultVo deleteTask(@PathVariable("taskId") Integer taskId) {
        return taskHandler.deleteTask(taskId);
    }

}
