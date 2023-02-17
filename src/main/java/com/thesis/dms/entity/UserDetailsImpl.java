package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String fullName;
    private String email;

    private Integer roleValue;

    private Long permission;
    private boolean status;
    private String avatar;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String fullName, String password,
                           Collection<? extends GrantedAuthority> authorities, Integer role, Long permission,
                           boolean status,
                           String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.authorities = authorities;
        this.roleValue = role;
        this.status = status;
        this.avatar = avatar;
        this.permission = permission;
    }

    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        Set<Long> permissionValueList = new HashSet<>();
        for (RoleEntity rolesEntity : user.getRoles()) {
            if (rolesEntity.getDeleted() == 0) {
                for (PermissionEntity permissionEntity : rolesEntity.getPermissionEntities()) {
                    permissionValueList.add(permissionEntity.getValue());
                }
            }
        }
        for (PermissionEntity permissionEntity : user.getPermissions()) {
            permissionValueList.add(permissionEntity.getValue());
        }
        Long permission = 0L;
        for (Long permissionValue : permissionValueList) {
            permission += permissionValue;
        }
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                authorities,
                user.getRole(),
                permission,
                true,
                user.getAvatar()

        );
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
