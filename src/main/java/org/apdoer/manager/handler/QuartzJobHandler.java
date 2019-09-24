//package org.apdoer.manager.handler;
//
//import org.apdoer.manager.model.pojo.QuartzJobPo;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Pageable;
//
///**
// * @author apdoer
// * @date 2019-01-07
// */
//@CacheConfig(cacheNames = "quartzJob")
//public interface QuartzJobHandler {
//
//    /**
//     * queryAll quartzJob
//     * @param criteria
//     * @param pageable
//     * @return
//     */
//    @Cacheable(keyGenerator = "keyGenerator")
//    Object queryAll(JobQueryCriteria criteria, Pageable pageable);
//
//    /**
//     * queryAll quartzLog
//     * @param criteria
//     * @param pageable
//     * @return
//     */
//    Object queryAllLog(JobQueryCriteria criteria, Pageable pageable);
//
//    /**
//     * create
//     * @param resources
//     * @return
//     */
//    @CacheEvict(allEntries = true)
//    QuartzJobPo create(QuartzJobPo resources);
//
//    /**
//     * update
//     * @param resources
//     * @return
//     */
//    @CacheEvict(allEntries = true)
//    void update(QuartzJobPo resources);
//
//    /**
//     * del
//     * @param quartzJob
//     */
//    @CacheEvict(allEntries = true)
//    void delete(QuartzJobPo quartzJob);
//
//    /**
//     * findById
//     * @param id
//     * @return
//     */
//    @Cacheable(key = "#p0")
//    QuartzJobPo findById(Long id);
//
//    /**
//     * 更改定时任务状态
//     * @param quartzJob
//     */
//    @CacheEvict(allEntries = true)
//    void updateIsPause(QuartzJobPo quartzJob);
//
//    /**
//     * 立即执行定时任务
//     * @param quartzJob
//     */
//    void execution(QuartzJobPo quartzJob);
//}
