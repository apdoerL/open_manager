package org.apdoer.manager.service.impl;
//
//import org.apdoer.manager.exception.EntityExistException;
//import org.apdoer.manager.exception.EntityNotFoundException;
//import org.apdoer.manager.mapper.UserMapper;
//import org.apdoer.manager.model.pojo.BizUserPo;
//import org.apdoer.manager.repository.UserRepository;
//import org.apdoer.manager.service.RedisService;
//import org.apdoer.manager.utils.PageUtil;
//import org.apdoer.manager.utils.QueryHelper;
//import org.apdoer.manager.utils.ValidationUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.mapper.UserMapper;
import org.apdoer.manager.model.pojo.BizUserPo;
import org.apdoer.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author apdoer
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;





    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }




    @Override
    public BizUserPo getUserByUsername(String username) {
        try {
            return userMapper.selectOne(BizUserPo.builder().username(username).build());
        }catch (Exception e){
            log.error("query mysql error;query user by name",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getPermissionList(Integer userId) {
        return null;
    }

    @Override
    public List<Integer> queryRolesByUserId(Integer userId) {
        return null;
    }
//
//    @Autowired
//    private UserRepository userRepository;
//

//
//    @Autowired
//    private RedisService redisService;
//
//    @Override
//    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
//        Page<BizUserPo> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(userMapper::toDto));
//    }
//
//    @Override
//    public UserDTO findById(long id) {
//        Optional<BizUserPo> user = userRepository.findById(id);
//        ValidationUtil.isNull(user,"User","id",id);
//        return userMapper.toDto(user.get());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public UserDTO create(BizUserPo resources) {
//
//        if(userRepository.findByUsername(resources.getUsername())!=null){
//            throw new EntityExistException(BizUserPo.class,"username",resources.getUsername());
//        }
//
//        if(userRepository.findByEmail(resources.getEmail())!=null){
//            throw new EntityExistException(BizUserPo.class,"email",resources.getEmail());
//        }
//
//        // 默认密码 123456，此密码是加密后的字符
//        resources.setPassword("e10adc3949ba59abbe56e057f20f883e");
//        resources.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
//        return userMapper.toDto(userRepository.save(resources));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(BizUserPo resources) {
//        Optional<BizUserPo> userOptional = userRepository.findById(resources.getId());
//        ValidationUtil.isNull(userOptional,"User","id",resources.getId());
//
//        BizUserPo user = userOptional.get();
//
//        BizUserPo user1 = userRepository.findByUsername(user.getUsername());
//        BizUserPo user2 = userRepository.findByEmail(user.getEmail());
//
//        if(user1 !=null&&!user.getId().equals(user1.getId())){
//            throw new EntityExistException(BizUserPo.class,"username",resources.getUsername());
//        }
//
//        if(user2!=null&&!user.getId().equals(user2.getId())){
//            throw new EntityExistException(BizUserPo.class,"email",resources.getEmail());
//        }
//
//        // 如果用户的角色改变了，需要手动清理下缓存
//        if (!resources.getRoles().equals(user.getRoles())) {
//            String key = "role::loadPermissionByUser:" + user.getUsername();
//            redisService.delete(key);
//            key = "role::findByUsers_Id:" + user.getId();
//            redisService.delete(key);
//        }
//
//        user.setUsername(resources.getUsername());
//        user.setEmail(resources.getEmail());
//        user.setEnabled(resources.getEnabled());
//        user.setRoles(resources.getRoles());
//        user.setDept(resources.getDept());
//        user.setJob(resources.getJob());
//        user.setPhone(resources.getPhone());
//        userRepository.save(user);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public UserDTO findByName(String userName) {
//        BizUserPo user = null;
//        if(ValidationUtil.isEmail(userName)){
//            user = userRepository.findByEmail(userName);
//        } else {
//            user = userRepository.findByUsername(userName);
//        }
//        if (user == null) {
//            throw new EntityNotFoundException(BizUserPo.class, "name", userName);
//        } else {
//            return userMapper.toDto(user);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updatePass(String username, String pass) {
//        userRepository.updatePass(username,pass,new Date());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateAvatar(String username, String url) {
//        userRepository.updateAvatar(username,url);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateEmail(String username, String email) {
//        userRepository.updateEmail(username,email);
//    }
}
