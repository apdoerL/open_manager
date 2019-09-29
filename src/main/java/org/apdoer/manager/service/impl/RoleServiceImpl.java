package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.EntityExistException;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.mapper.RoleMapper;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.PermissionVo;
import org.apdoer.manager.model.vo.RoleIdDescVo;
import org.apdoer.manager.model.vo.RoleVo;
import org.apdoer.manager.service.RoleService;
import org.apdoer.manager.utils.PageUtil;
import org.apdoer.manager.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author apdoer
 * @date 2018-12-03
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;

    @Autowired
    @SuppressWarnings("all")
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleVo> queryRoleList(RoleVo roleVo) {
        try {
            return roleMapper.queryRoleList(roleVo);
        }catch (Exception e){
            log.error("query role list error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    @Cacheable(cacheManager = "webRedisCacheManager",value = "COMMON",key = "'COMMON_allCreatedRole")
    public List<RoleIdDescVo> queryAllCreatedRole() {
        try {
            return roleMapper.queryAllCreatedRole();
        }catch (Exception e){
            log.error("query all created role error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public List<PermissionVo> queryAllPerm() {
        try {
            return roleMapper.queryAllPerm();
        }catch (Exception e){
            log.error("query perm List error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private RoleMapper roleMapper;
//
//    @Autowired
//    private RoleSmallMapper roleSmallMapper;
//
//    @Override
//    public Object queryAll(Pageable pageable) {
//        return roleMapper.toDto(roleRepository.findAll(pageable).getContent());
//    }
//
//    @Override
//    public Object queryAll(CommonQueryCriteria criteria, Pageable pageable) {
//        Page<RolePo> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(roleMapper::toDto));
//    }
//
//    @Override
//    public RoleDTO findById(long id) {
//        Optional<RolePo> role = roleRepository.findById(id);
//        ValidationUtil.isNull(role,"Role","id",id);
//        return roleMapper.toDto(role.get());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public RoleDTO create(RolePo resources) {
//        if(roleRepository.findByName(resources.getName()) != null){
//            throw new EntityExistException(RolePo.class,"username",resources.getName());
//        }
//        return roleMapper.toDto(roleRepository.save(resources));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(RolePo resources) {
//
//        Optional<RolePo> optionalRole = roleRepository.findById(resources.getId());
//        ValidationUtil.isNull(optionalRole,"Role","id",resources.getId());
//
//        RolePo role = optionalRole.get();
//
//        RolePo role1 = roleRepository.findByName(resources.getName());
//
//        if(role1 != null && !role1.getId().equals(role.getId())){
//            throw new EntityExistException(RolePo.class,"username",resources.getName());
//        }
//
//        role.setName(resources.getName());
//        role.setRemark(resources.getRemark());
//        role.setDataScope(resources.getDataScope());
//        role.setDepts(resources.getDepts());
//        role.setLevel(resources.getLevel());
//        roleRepository.save(role);
//    }
//
//    @Override
//    public void updatePermission(RolePo resources, RoleDTO roleDTO) {
//        RolePo role = roleMapper.toEntity(roleDTO);
//        role.setPermissions(resources.getPermissions());
//        roleRepository.save(role);
//    }
//
//    @Override
//    public void updateMenu(RolePo resources, RoleDTO roleDTO) {
//        RolePo role = roleMapper.toEntity(roleDTO);
//        role.setMenus(resources.getMenus());
//        roleRepository.save(role);
//    }
//
//    @Override
//    public void untiedMenu(MenuPo menu) {
//        Set<RolePo> roles = roleRepository.findByMenus_Id(menu.getId());
//        for (RolePo role : roles) {
//            menu.getRoles().remove(role);
//            role.getMenus().remove(menu);
//            roleRepository.save(role);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long id) {
//        roleRepository.deleteById(id);
//    }
//
//    @Override
//    public List<RoleSmallDTO> findByUsers_Id(Long id) {
//        return roleSmallMapper.toDto(roleRepository.findByUsers_Id(id).stream().collect(Collectors.toList()));
//    }
//
//    @Override
//    public Integer findByRoles(Set<RolePo> roles) {
//        Set<RoleDTO> roleDTOS = new HashSet<>();
//        for (RolePo role : roles) {
//            roleDTOS.add(findById(role.getId()));
//        }
//        return Collections.min(roleDTOS.stream().map(RoleDTO::getLevel).collect(Collectors.toList()));
//    }
}
