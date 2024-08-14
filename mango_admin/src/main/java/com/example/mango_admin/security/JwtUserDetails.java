package com.example.mango_admin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private String username;
    private String password;
    private String salt;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(String name, String password, String salt, List<GrantedAuthority> grantedAuthorities) {
        this.username = name;
        this.password = password;
        this.salt = salt;
        this.authorities = grantedAuthorities;
    }

    public String getSalt() {
        return salt;
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
        return username;
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
