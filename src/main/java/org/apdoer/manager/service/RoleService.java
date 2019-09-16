package org.apdoer.manager.service;

import org.apdoer.manager.model.dto.CommonQueryCriteria;
import org.apdoer.manager.model.dto.RoleDTO;
import org.apdoer.manager.model.dto.RoleSmallDTO;
import org.apdoer.manager.model.pojo.MenuPo;
import org.apdoer.manager.model.pojo.RolePo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


/**
 * @author apdoer
 * @date 2018-12-03
 */
@CacheConfig(cacheNames = "role")
public interface RoleService {

    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    RoleDTO findById(long id);

    /**
     * create
     * @param rolePo
     * @return
     */
    @CacheEvict(allEntries = true)
    RoleDTO create(RolePo rolePo);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(RolePo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * key的名称如有修改，请同步修改 UserServiceImpl 中的 update 方法
     * findByUsers_Id
     * @param id
     * @return
     */
    @Cacheable(key = "'findByUsers_Id:' + #p0")
    List<RoleSmallDTO> findByUsers_Id(Long id);

    @Cacheable(keyGenerator = "keyGenerator")
    Integer findByRoles(Set<RolePo> roles);

    /**
     * updatePermission
     * @param resources
     * @param roleDTO
     */
    @CacheEvict(allEntries = true)
    void updatePermission(RolePo resources, RoleDTO roleDTO);

    /**
     * updateMenu
     * @param resources
     * @param roleDTO
     */
    @CacheEvict(allEntries = true)
    void updateMenu(RolePo resources, RoleDTO roleDTO);

    @CacheEvict(allEntries = true)
    void untiedMenu(MenuPo menu);

    /**
     * queryAll
     * @param pageable
     * @return
     */
    Object queryAll(Pageable pageable);

    /**
     * queryAll
     * @param pageable
     * @param criteria
     * @return
     */
    Object queryAll(CommonQueryCriteria criteria, Pageable pageable);
}
