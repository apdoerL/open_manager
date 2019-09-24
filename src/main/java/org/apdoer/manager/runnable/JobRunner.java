//package org.apdoer.manager.runnable;
//
//import org.apdoer.manager.model.pojo.QuartzJobPo;
//import org.apdoer.manager.repository.QuartzJobRepository;
//import org.apdoer.manager.utils.QuartzManage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author apdoer
// * @date 2019-01-07
// */
//@Component
//public class JobRunner implements ApplicationRunner {
//
//    @Autowired
//    private QuartzJobRepository quartzJobRepository;
//
//    @Autowired
//    private QuartzManage quartzManage;
//
//    /**
//     * 项目启动时重新激活启用的定时任务
//     * @param applicationArguments
//     * @throws Exception
//     */
//    @Override
//    public void run(ApplicationArguments applicationArguments){
//        System.out.println("--------------------注入定时任务---------------------");
//        List<QuartzJobPo> quartzJobs = quartzJobRepository.findByIsPauseIsFalse();
//        quartzJobs.forEach(quartzJob -> {
//            quartzManage.addJob(quartzJob);
//        });
//        System.out.println("--------------------定时任务注入完成---------------------");
//    }
//}
