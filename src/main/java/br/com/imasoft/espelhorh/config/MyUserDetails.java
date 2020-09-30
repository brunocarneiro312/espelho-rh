package br.com.imasoft.espelhorh.config;

import br.com.imasoft.espelhorh.model.User;
import br.com.imasoft.espelhorh.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.active = true;
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            authorities = new ArrayList<>(Collections.singletonList(
                    new SimpleGrantedAuthority(RoleEnum.ROLE_COMMON.getRoleName())));
        }
        else {
            this.authorities = user.getRoles().stream()
                    .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.active;
    }
}
