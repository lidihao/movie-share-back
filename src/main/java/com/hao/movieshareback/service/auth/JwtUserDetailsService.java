package com.hao.movieshareback.service.auth;

import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.service.PictureService;
import com.hao.movieshareback.service.RoleService;
import com.hao.movieshareback.service.UserService;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("jwtUserDetailsService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PictureService pictureService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user==null||user.isDelete()){
            throw new UsernameNotFoundException("用户不存在或者已经被删除");
        }
        List<Role> roleList = roleService.getRolesByUserName(username);
        List<GrantedAuthority> grantedAuthorityList = roleList.stream().
                map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        Picture avatarPic = pictureService.getPicById(user.getAvatarPicId());
        String avatarUrl="/image/default-avatar.jpeg";
        if (avatarPic!=null){
            avatarUrl=avatarPic.getUrl();
        }
        return new JwtUser(user.getUserId(),user.getUserName(),user.getSalt(),user.getPassword(),
                avatarUrl,user.getEmail(),grantedAuthorityList,user.isHasActive(),user.getLastPasswordResetDate(),user.getCreatedTime()
        );
    }
}
