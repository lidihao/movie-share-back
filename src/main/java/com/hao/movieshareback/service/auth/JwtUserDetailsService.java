package com.hao.movieshareback.service.auth;

import com.hao.movieshareback.model.Permission;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.service.PermissionService;
import com.hao.movieshareback.service.UserService;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user==null||user.isDelete()){
            throw new UsernameNotFoundException("用户不存在或者已经被删除");
        }
        List<Permission> permissionList = permissionService.getPermissionByUserName(user.getUserName());
        List<GrantedAuthority> grantedAuthorityList = permissionList.stream().
                map(permission -> new SimpleGrantedAuthority(permission.getPermissionAction()))
                .collect(Collectors.toList());
        return null;
    }
}
