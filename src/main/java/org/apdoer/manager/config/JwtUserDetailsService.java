package org.apdoer.manager.config;

import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.model.pojo.BizUserPo;
import org.apdoer.manager.model.vo.JwtUser;
import org.apdoer.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


/**
 * @author apdoer
 * @date 2018-11-22
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {
    private UserService userService;
    private JwtPermissionService permissionService;




    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPermissionService(JwtPermissionService permissionService) {
        this.permissionService = permissionService;
    }



    @Override
    public UserDetails loadUserByUsername(String username){
        BizUserPo user = userService.getUserByUsername(username);
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(user);
        }
    }

    private UserDetails createJwtUser(BizUserPo user) {
        return JwtUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createTime(user.getCreateTime())
                .enabled(user.getEnabled())
                .permissions(permissionService.getPerms(user))
                .authorities(permissionService.getAuthoritis(user))
                .build();
    }
}
