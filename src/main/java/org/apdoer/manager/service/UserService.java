package org.apdoer.manager.service;
//
//import org.apdoer.manager.model.pojo.BizUserPo;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Pageable;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.pojo.BizUserPo;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.UserVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

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
     * @param roleIds userIds 该方法需要加缓存
     * @return perms
     */
    List<String> getPermissionList(Integer roleIds);

    /**
     * 根据userId 查询角色  该方法需要加缓存
     * @param userId userId
     * @return r
     */
    List<Integer> queryRolesByUserId(Long userId);

    /**
     * 根据userid 查询rolepos
     * @param id userid
     * @return rolepos
     */
    List<RolePo> queryRolePosByUserId(Long id);



//    @Cacheable(cacheManager = "webRedisCacheManager", value = "USER_NAME",key = "'BIZ_USER_PERMS:'+#user.username")

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
