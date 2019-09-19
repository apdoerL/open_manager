package org.apdoer.manager.config;

import org.apdoer.manager.model.pojo.BizUserPo;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author apdoer
 */
@Component
public class JwtPermissionService {
    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }




    /**
     *
     * @param user user
     * @return perms
     */
    public Collection<GrantedAuthority> getAuthoritis(BizUserPo user) {
        List<RolePo> rolePos = userService.queryRolePosByUserId(user.getId());
        HashSet<GrantedAuthority> authorize = new HashSet<>(rolePos.size());
        rolePos.forEach(var-> authorize.add(new SimpleGrantedAuthority(var.getName())));
        return authorize;
    }

    public Set<String> getPerms(BizUserPo user) {
        List<Integer> roleIds = userService.queryRolesByUserId(user.getId());
        Set<String> perms = new HashSet<>();
        roleIds.forEach(roleId->perms.addAll(userService.getPermissionList(roleId)));
        return perms;
    }
}
