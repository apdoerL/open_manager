package org.apdoer.manager.service;

import org.apdoer.manager.model.dto.CommonQueryCriteria;
import org.apdoer.manager.model.dto.MenuDTO;
import org.apdoer.manager.model.dto.RoleSmallDTO;
import org.apdoer.manager.model.pojo.MenuPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author apdoer
 * @date 2018-12-17
 */
@CacheConfig(cacheNames = "menu")
public interface MenuService {

    /**
     * queryAll
     * @param criteria
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<MenuDTO> queryAll(CommonQueryCriteria criteria);

    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MenuDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MenuDTO create(MenuPo resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(MenuPo resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * permission tree
     * @return
     */
    @Cacheable(key = "'tree'")
    Object getMenuTree(List<MenuPo> menus);

    /**
     * findByPid
     * @param pid
     * @return
     */
    @Cacheable(key = "'pid:'+#p0")
    List<MenuPo> findByPid(long pid);

    /**
     * build Tree
     * @param menuDTOS
     * @return
     */
    Map buildTree(List<MenuDTO> menuDTOS);

    /**
     * findByRoles
     * @param roles
     * @return
     */
    List<MenuDTO> findByRoles(List<RoleSmallDTO> roles);

    /**
     * buildMenus
     * @param byRoles
     * @return
     */
    Object buildMenus(List<MenuDTO> byRoles);

    MenuPo findOne(Long id);
}
