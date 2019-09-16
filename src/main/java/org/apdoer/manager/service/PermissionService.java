package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.PermissionPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-08
 */
@CacheConfig(cacheNames = "permission")
public interface PermissionService {

//    /**
//     * get
//     * @param id
//     * @return
//     */
//    @Cacheable(key = "#p0")
//    PermissionDTO findById(long id);
//
//    /**
//     * create
//     * @param resources
//     * @return
//     */
//    @CacheEvict(allEntries = true)
//    PermissionDTO create(PermissionPo resources);
//
//    /**
//     * update
//     * @param resources
//     */
//    @CacheEvict(allEntries = true)
//    void update(PermissionPo resources);
//
//    /**
//     * delete
//     * @param id
//     */
//    @CacheEvict(allEntries = true)
//    void delete(Long id);
//
//    /**
//     * permission tree
//     * @return
//     */
//    @Cacheable(key = "'tree'")
//    Object getPermissionTree(List<PermissionPo> permissions);
//
//    /**
//     * findByPid
//     * @param pid
//     * @return
//     */
//    @Cacheable(key = "'pid:'+#p0")
//    List<PermissionPo> findByPid(long pid);
//
//    /**
//     * build Tree
//     * @param permissionDTOS
//     * @return
//     */
//    @Cacheable(keyGenerator = "keyGenerator")
//    Object buildTree(List<PermissionDTO> permissionDTOS);
//
//    /**
//     * queryAll
//     * @param criteria
//     * @return
//     */
//    @Cacheable(keyGenerator = "keyGenerator")
//    List<PermissionDTO> queryAll(CommonQueryCriteria criteria);
}
