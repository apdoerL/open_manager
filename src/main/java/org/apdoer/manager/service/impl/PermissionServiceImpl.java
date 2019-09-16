//package org.apdoer.manager.service.impl;
//
//import org.apdoer.manager.exception.BadRequestException;
//import org.apdoer.manager.exception.EntityExistException;
//import org.apdoer.manager.mapper.PermissionMapper;
//import org.apdoer.manager.model.pojo.PermissionPo;
//import org.apdoer.manager.repository.PermissionRepository;
//import org.apdoer.manager.service.PermissionService;
//import org.apdoer.manager.utils.QueryHelper;
//import org.apdoer.manager.utils.ValidationUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
///**
// * @author apdoer
// * @date 2018-12-03
// */
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class PermissionServiceImpl implements PermissionService {
//
//    @Autowired
//    private PermissionRepository permissionRepository;
//
//    @Autowired
//    private PermissionMapper permissionMapper;
//
//    @Override
//    public List<PermissionDTO> queryAll(CommonQueryCriteria criteria) {
//        return permissionMapper.toDto(permissionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder)));
//    }
//
//    @Override
//    public PermissionDTO findById(long id) {
//        Optional<PermissionPo> permission = permissionRepository.findById(id);
//        ValidationUtil.isNull(permission,"Permission","id",id);
//        return permissionMapper.toDto(permission.get());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public PermissionDTO create(PermissionPo resources) {
//        if(permissionRepository.findByName(resources.getName()) != null){
//            throw new EntityExistException(PermissionPo.class,"name",resources.getName());
//        }
//        return permissionMapper.toDto(permissionRepository.save(resources));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(PermissionPo resources) {
//        Optional<PermissionPo> optionalPermission = permissionRepository.findById(resources.getId());
//        if(resources.getId().equals(resources.getPid())) {
//            throw new BadRequestException("上级不能为自己");
//        }
//        ValidationUtil.isNull(optionalPermission,"Permission","id",resources.getId());
//
//        PermissionPo permission = optionalPermission.get();
//
//        PermissionPo permission1 = permissionRepository.findByName(resources.getName());
//
//        if(permission1 != null && !permission1.getId().equals(permission.getId())){
//            throw new EntityExistException(PermissionPo.class,"name",resources.getName());
//        }
//
//        permission.setName(resources.getName());
//        permission.setAlias(resources.getAlias());
//        permission.setPid(resources.getPid());
//        permissionRepository.save(permission);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long id) {
//        List<PermissionPo> permissionList = permissionRepository.findByPid(id);
//        for (PermissionPo permission : permissionList) {
//            permissionRepository.delete(permission);
//        }
//        permissionRepository.deleteById(id);
//    }
//
//    @Override
//    public Object getPermissionTree(List<PermissionPo> permissions) {
//        List<Map<String,Object>> list = new LinkedList<>();
//        permissions.forEach(permission -> {
//                    if (permission!=null){
//                        List<PermissionPo> permissionList = permissionRepository.findByPid(permission.getId());
//                        Map<String,Object> map = new HashMap<>();
//                        map.put("id",permission.getId());
//                        map.put("label",permission.getAlias());
//                        if(permissionList!=null && permissionList.size()!=0){
//                            map.put("children",getPermissionTree(permissionList));
//                        }
//                        list.add(map);
//                    }
//                }
//        );
//        return list;
//    }
//
//    @Override
//    public List<PermissionPo> findByPid(long pid) {
//        return permissionRepository.findByPid(pid);
//    }
//
//    @Override
//    public Object buildTree(List<PermissionDTO> permissionDTOS) {
//
//        List<PermissionDTO> trees = new ArrayList<PermissionDTO>();
//
//        for (PermissionDTO permissionDTO : permissionDTOS) {
//
//            if ("0".equals(permissionDTO.getPid().toString())) {
//                trees.add(permissionDTO);
//            }
//
//            for (PermissionDTO it : permissionDTOS) {
//                if (it.getPid().equals(permissionDTO.getId())) {
//                    if (permissionDTO.getChildren() == null) {
//                        permissionDTO.setChildren(new ArrayList<PermissionDTO>());
//                    }
//                    permissionDTO.getChildren().add(it);
//                }
//            }
//        }
//
//        Integer totalElements = permissionDTOS!=null?permissionDTOS.size():0;
//
//        Map map = new HashMap();
//        map.put("content",trees.size() == 0?permissionDTOS:trees);
//        map.put("totalElements",totalElements);
//        return map;
//    }
//}
