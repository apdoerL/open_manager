package org.apdoer.manager.service;
//
//import org.apdoer.manager.model.pojo.BizUserPo;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Pageable;

import org.apdoer.manager.model.pojo.BizUserPo;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @author apdoer
 * @date 2018-11-23
 */
//@CacheConfig(cacheNames = "user")
public interface UserService {

    /***
     * 根据用户名查找用户
     * @param username userName
     * @return
     */
    BizUserPo getUserByUsername(String username);

    /***
     * 获取用户权限
     * @param userId userId
     * @return r
     */
    List<String> getPermissionList(Integer userId);

    /**
     * 根据userId 查询角色
     * @param userId userId
     * @return r
     */
    List<Integer> queryRolesByUserId(Integer userId);


//
////    /**
////     * get
////     * @param id
////     * @return
////     */
////    @Cacheable(key = "#p0")
////    UserDTO findById(long id);
////
////    /**
////     * create
////     * @param resources
////     * @return
////     */
////    @CacheEvict(allEntries = true)
////    UserDTO create(BizUserPo resources);
////
////    /**
////     * update
////     * @param resources
////     */
////    @CacheEvict(allEntries = true)
////    void update(BizUserPo resources);
////
////    /**
////     * delete
////     * @param id
////     */
////    @CacheEvict(allEntries = true)
////    void delete(Long id);
////
////    /**
////     * findByName
////     * @param userName
////     * @return
////     */
////    @Cacheable(key = "'loadUserByUsername:'+#p0")
////    UserDTO findByName(String userName);
////
////    /**
////     * 修改密码
////     * @param username
////     * @param encryptPassword
////     */
////    @CacheEvict(allEntries = true)
////    void updatePass(String username, String encryptPassword);
////
////    /**
////     * 修改头像
////     * @param username
////     * @param url
////     */
////    @CacheEvict(allEntries = true)
////    void updateAvatar(String username, String url);
////
////    /**
////     * 修改邮箱
////     * @param username
////     * @param email
////     */
////    @CacheEvict(allEntries = true)
////    void updateEmail(String username, String email);
////
////    @Cacheable(keyGenerator = "keyGenerator")
////    Object queryAll(UserQueryCriteria criteria, Pageable pageable);
}
