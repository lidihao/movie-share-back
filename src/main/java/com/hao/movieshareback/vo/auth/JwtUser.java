package com.hao.movieshareback.vo.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final Integer userId;

    private final String userName;

    private final String salt;

    private final String password;

    private final String avatarUrl;

    private final String email;

    private final Collection<GrantedAuthority> authorities;

    private boolean hasActive;

    private final Date lastPasswordResetDate;

    private Date createTime;

    public JwtUser(Integer userId, String username, String salt, String password,
                   String avatarUrl, String email, Collection<GrantedAuthority> authorities,
                   boolean hasActive, Date lastPasswordResetDate, Date createTime) {
        this.userId = userId;
        this.userName = username;
        this.salt = salt;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.authorities = authorities;
        this.hasActive = hasActive;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.createTime = createTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return hasActive;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSalt() {
        return salt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasActive() {
        return hasActive;
    }

    public void setHasActive(boolean hasActive) {
        this.hasActive = hasActive;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
