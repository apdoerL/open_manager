package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.JobDTO;
import org.apdoer.manager.model.dto.JobQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author apdoer
* @date 2019-03-29
*/
@CacheConfig(cacheNames = "job")
public interface JobHandler {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    JobDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    JobDTO create(JobPo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(JobPo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * queryAll
     * @param criteria
     * @param pageable
     * @return
     */
    Object queryAll(JobQueryCriteria criteria, Pageable pageable);
}