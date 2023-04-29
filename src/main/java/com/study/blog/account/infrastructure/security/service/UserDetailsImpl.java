package com.study.blog.account.infrastructure.security.service;

import com.study.blog.account.domain.Role;
import com.study.blog.account.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
public class UserDetailsImpl implements UserDetails, CustomUserDetails {
    private final User user;
    private final Set<Role> roles;

    public UserDetailsImpl(User user, Set<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user, user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return true;
    }
}
