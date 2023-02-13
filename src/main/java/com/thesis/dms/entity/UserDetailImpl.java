package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private int role;
    private int active;
    private Long permission;
    private String avatar;
    private String userCode;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String username, String email, String fullName,
                          String password, Collection<? extends GrantedAuthority> authorities, int role,
                           int active, Long permission, String avatar, String userCode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.authorities = authorities;
        this.role = role;
        this.active = active;
        this.permission = permission;
        this.avatar = avatar;
        this.userCode = userCode;
    }

    public static UserDetailImpl build(UserEntity user) {
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
        return new UserDetailImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                authorities,
                user.getRole(),
                user.getActive(),
                permission,
                user.getAvatar(),
                user.getUserCode()
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

}
