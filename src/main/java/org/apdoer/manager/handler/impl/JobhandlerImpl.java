//package org.apdoer.manager.handler.impl;
//
//import org.apdoer.manager.handler.JobHandler;
//import org.apdoer.manager.mapper.JobMapper;
//import org.apdoer.manager.repository.DeptRepository;
//import org.apdoer.manager.repository.JobRepository;
//import org.apdoer.manager.utils.PageUtil;
//import org.apdoer.manager.utils.QueryHelper;
//import org.apdoer.manager.utils.ValidationUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
//* @author apdoer
//* @date 2019-03-29
//*/
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class JobhandlerImpl implements JobHandler {
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Autowired
//    private JobMapper jobMapper;
//
//    @Autowired
//    private DeptRepository deptRepository;
//
//    @Override
//    public Object queryAll(JobQueryCriteria criteria, Pageable pageable) {
//        Page<JobPo> page = jobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable);
//        List<JobDTO> jobs = new ArrayList<>();
//        for (JobPo job : page.getContent()) {
//            jobs.add(jobMapper.toDto(job,deptRepository.findNameById(job.getDept().getPid())));
//        }
//        return PageUtil.toPage(jobs,page.getTotalElements());
//    }
//
//    @Override
//    public JobDTO findById(Long id) {
//        Optional<JobPo> job = jobRepository.findById(id);
//        ValidationUtil.isNull(job,"Job","id",id);
//        return jobMapper.toDto(job.get());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public JobDTO create(JobPo resources) {
//        return jobMapper.toDto(jobRepository.save(resources));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(JobPo resources) {
//        Optional<JobPo> optionalJob = jobRepository.findById(resources.getId());
//        ValidationUtil.isNull( optionalJob,"Job","id",resources.getId());
//
//        JobPo job = optionalJob.get();
//        resources.setId(job.getId());
//        jobRepository.save(resources);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long id) {
//        jobRepository.deleteById(id);
//    }
//}