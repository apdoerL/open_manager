//package org.apdoer.manager.config;
//import lombok.extern.slf4j.Slf4j;
//import org.apdoer.manager.handler.QuartzJobHandler;
//import org.apdoer.manager.model.pojo.JobRecordPo;
//import org.apdoer.manager.model.pojo.QuartzJobPo;
//import org.apdoer.manager.repository.QuartzLogRepository;
//import org.apdoer.manager.utils.QuartzManage;
//import org.apdoer.manager.runnable.QuartzRunnable;
//import org.apdoer.manager.utils.SpringContextHolder;
//import org.apdoer.manager.utils.ThrowableUtil;
//import org.quartz.JobExecutionContext;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// * 参考人人开源，https://gitee.com/renrenio/renren-security
// * @author
// * @date 2019-01-07
// */
//@Async
//@Slf4j
//public class ExecutionJob extends QuartzJobBean {
//
//    private ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    @Override
//    protected void executeInternal(JobExecutionContext context) {
//        QuartzJobPo quartzJob = (QuartzJobPo) context.getMergedJobDataMap().get(QuartzJobPo.JOB_KEY);
//        // 获取spring bean
//        QuartzLogRepository quartzLogRepository = SpringContextHolder.getBean("quartzLogRepository");
//        QuartzJobHandler quartzJobService = SpringContextHolder.getBean("quartzJobService");
//        QuartzManage quartzManage = SpringContextHolder.getBean("quartzManage");
//
//        JobRecordPo quartzLog = new JobRecordPo();
//        quartzLog.setJobName(quartzJob.getJobName());
//        quartzLog.setBeanName(quartzJob.getBeanName());
//        quartzLog.setMethodName(quartzJob.getMethodName());
//        quartzLog.setParams(quartzJob.getParams());
//        long startTime = System.currentTimeMillis();
//        quartzLog.setCronExpression(quartzJob.getCronExpression());
//        try {
//            // 执行任务
//            log.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
//            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
//                    quartzJob.getParams());
//            Future<?> future = executorService.submit(task);
//            future.get();
//            long times = System.currentTimeMillis() - startTime;
//            quartzLog.setTime(times);
//            // 任务状态
//            quartzLog.setIsSuccess(true);
//            log.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
//        } catch (Exception e) {
//            log.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
//            long times = System.currentTimeMillis() - startTime;
//            quartzLog.setTime(times);
//            // 任务状态 0：成功 1：失败
//            quartzLog.setIsSuccess(false);
//            quartzLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
//            //出错就暂停任务
//            quartzManage.pauseJob(quartzJob);
//            //更新状态
//            quartzJobService.updateIsPause(quartzJob);
//        } finally {
//            quartzLogRepository.save(quartzLog);
//        }
//    }
//}
