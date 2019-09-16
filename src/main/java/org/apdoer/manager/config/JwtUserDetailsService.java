//package org.apdoer.manager.config;
//
//import org.apdoer.manager.exception.BadRequestException;
//import org.apdoer.manager.model.vo.JwtUser;
//import org.apdoer.manager.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
///**
// * @author apdoer
// * @date 2018-11-22
// */
//@Component
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtPermissionService permissionService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username){
//
//        UserDTO user = userService.findByName(username);
//        if (user == null) {
//            throw new BadRequestException("账号不存在");
//        } else {
//            return createJwtUser(user);
//        }
//    }
//
//    public UserDetails createJwtUser(UserDTO user) {
//        return new JwtUser(
//                user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getAvatar(),
//                user.getEmail(),
//                user.getPhone(),
//                Optional.ofNullable(user.getDept()).map(DeptSmallDTO::getName).orElse(null),
//                Optional.ofNullable(user.getJob()).map(JobSmallDTO::getName).orElse(null),
//                permissionService.mapToGrantedAuthorities(user),
//                user.getEnabled(),
//                user.getCreateTime(),
//                user.getLastPasswordResetTime()
//        );
//    }
//}
