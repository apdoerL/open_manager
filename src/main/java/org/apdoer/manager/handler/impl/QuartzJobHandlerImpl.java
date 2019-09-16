package org.apdoer.manager.handler.impl;

import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.handler.QuartzJobHandler;
import org.apdoer.manager.model.dto.JobQueryCriteria;
import org.apdoer.manager.model.pojo.QuartzJobPo;
import org.apdoer.manager.repository.QuartzJobRepository;
import org.apdoer.manager.repository.QuartzLogRepository;
import org.apdoer.manager.utils.PageUtil;
import org.apdoer.manager.utils.QuartzManage;
import org.apdoer.manager.utils.QueryHelper;
import org.apdoer.manager.utils.ValidationUtil;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Service(value = "quartzJobService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzJobHandlerImpl implements QuartzJobHandler {

    @Autowired
    private QuartzJobRepository quartzJobRepository;

    @Autowired
    private QuartzLogRepository quartzLogRepository;

    @Autowired
    private QuartzManage quartzManage;

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public Object queryAllLog(JobQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public QuartzJobPo findById(Long id) {
        Optional<QuartzJobPo> quartzJob = quartzJobRepository.findById(id);
        ValidationUtil.isNull(quartzJob,"QuartzJob","id",id);
        return quartzJob.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuartzJobPo create(QuartzJobPo resources) {
        if (!CronExpression.isValidExpression(resources.getCronExpression())){
            throw new BadRequestException("cron表达式格式错误");
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.addJob(resources);
        return resources;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJobPo resources) {
        if(resources.getId().equals(1L)){
            throw new BadRequestException("该任务不可操作");
        }
        if (!CronExpression.isValidExpression(resources.getCronExpression())){
            throw new BadRequestException("cron表达式格式错误");
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.updateJobCron(resources);
    }

    @Override
    public void updateIsPause(QuartzJobPo quartzJob) {
        if(quartzJob.getId().equals(1L)){
            throw new BadRequestException("该任务不可操作");
        }
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobRepository.save(quartzJob);
    }

    @Override
    public void execution(QuartzJobPo quartzJob) {
        if(quartzJob.getId().equals(1L)){
            throw new BadRequestException("该任务不可操作");
        }
        quartzManage.runAJobNow(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(QuartzJobPo quartzJob) {
        if(quartzJob.getId().equals(1L)){
            throw new BadRequestException("该任务不可操作");
        }
        quartzManage.deleteJob(quartzJob);
        quartzJobRepository.delete(quartzJob);
    }
}
