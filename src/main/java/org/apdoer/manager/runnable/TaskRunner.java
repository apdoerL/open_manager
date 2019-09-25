package org.apdoer.manager.runnable;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.mapper.TaskMapper;
import org.apdoer.manager.model.pojo.QuartzJobPo;
import org.apdoer.manager.config.QuartzManageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Component
@Slf4j
public class TaskRunner implements ApplicationRunner {
    private TaskMapper taskMapper;
    private QuartzManageConfig quartzManage;


    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }
    @Autowired
    public void setQuartzManage(QuartzManageConfig quartzManage) {
        this.quartzManage = quartzManage;
    }


    /**
     * 项目启动时重新激活启用的定时任务
     * @param applicationArguments a
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        log.info("===============task initial=========================");
        List<QuartzJobPo> quartzJobs = taskMapper.queryEnabledTask();
        log.info("===");
        quartzJobs.forEach(quartzJob -> quartzManage.addJob(quartzJob));
        log.info("===============task initial success=========================");
    }
}
