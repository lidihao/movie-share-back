package com.hao.movieshareback.vo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    @JsonIgnore
    private final Integer userId;

    private final String username;

    @JsonIgnore
    private final String password;

    private final String avatarUrl;

    private final String email;

    @JsonIgnore
    private final Collection<GrantedAuthority> authorities;

    private boolean hasActive;

    @JsonIgnore
    private final Date lastPasswordResetDate;

    private Date createTime;

    public JwtUser(Integer userId, String username, String password, String avatarUrl,
                   String email, Collection<GrantedAuthority> authorities,
                   boolean hasActive, Date lastPasswordResetDate, Date createTime) {
        this.userId = userId;
        this.username = username;
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
        return password;
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
