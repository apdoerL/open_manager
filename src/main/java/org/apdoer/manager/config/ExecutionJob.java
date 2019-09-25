package org.apdoer.manager.config;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.TaskStatusEnum;
import org.apdoer.manager.handler.TaskHandler;
import org.apdoer.manager.mapper.TaskLogMapper;
import org.apdoer.manager.model.pojo.TaskLogPo;
import org.apdoer.manager.model.pojo.QuartzJobPo;
import org.apdoer.manager.runnable.TaskExecuteRunnable;
import org.apdoer.manager.utils.SpringContextHolder;
import org.apdoer.manager.utils.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Async
@Slf4j
public class ExecutionJob extends QuartzJobBean {

    private ExecutorService executorService = OpenManagerSingleThreadPool.getInstance();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJobPo quartzJob = (QuartzJobPo) context.getMergedJobDataMap().get(QuartzJobPo.JOB_KEY);
        // 获取spring bean
        TaskLogMapper taskLogMapper = SpringContextHolder.getBean("taskLogMapper");
        TaskHandler quartzJobService = SpringContextHolder.getBean("taskHandlerImpl");
        QuartzManageConfig quartzManage = SpringContextHolder.getBean("quartzManageConfig");

        long startTime = System.currentTimeMillis();
        TaskLogPo taskLogPo = TaskLogPo.builder()
                            .taskName(quartzJob.getTaskName())
                            .beanName(quartzJob.getBeanName())
                            .methodName(quartzJob.getMethodName())
                            .params(quartzJob.getParams())
                            .createTime(new Date())
                            .cronExpression(quartzJob.getCronExpression()).build();
        try {
            log.info("task is executing，taskName：{}", quartzJob.getTaskName());

            TaskExecuteRunnable task = new TaskExecuteRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            executorService.submit(task).get();
            taskLogPo.setTimeConsuming(System.currentTimeMillis() - startTime);
            // 任务状态
            taskLogPo.setStatus(TaskStatusEnum.ENABLED.getStatus());

            log.info("task execute success，taskName：{} timeConsuming：{}ms", quartzJob.getTaskName(), System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("task execute failed，taskName：{}" + quartzJob.getTaskName(), e);

            long endTime = System.currentTimeMillis() - startTime;
            taskLogPo.setTimeConsuming(endTime);
            taskLogPo.setStatus(TaskStatusEnum.PAUSE.getStatus());
            taskLogPo.setExceptionDetail(ThrowableUtil.getStackTrace(e));

            //出错就暂停任务
            quartzManage.pauseJob(quartzJob);
            //更新状态
            quartzJobService.updateTaskStatus(quartzJob.getId(),quartzJob.getStatus());
        } finally {
            taskLogMapper.insertSelective(taskLogPo);
        }
    }
}
