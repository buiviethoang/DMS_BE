package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column(name = "full_name")
    private String fullName;

    /**
     * Ten dang nhap
     */
    @Column(name = "username")
    private String username;
    /**
     * anh dai dien
     */
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private boolean status;

    @Column(name = "code")
    private String code;
    /**
     * Dia chi email
     */
    @Column(name = "email")
    private String email;
    /**
     * Mat khau dang nhap
     */
    @JsonIgnore
    @Column(name = "password")
    private String password;

    /**
     * so dien thoai
     */
    @Column(name = "phone")
    private String phone;


    @Column(name = "role")
    private Integer role;

    @Column(name = "loginToken")
    private String loginToken;

    @Column(name = "refreshToken")
    private String refreshToken;

    /**
     * Role cua nguoi dung
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    /**
     * Permission nguoi dung
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_permissions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}
