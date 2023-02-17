package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "apply_user_type")
    private Integer applyUserType;
    @Column(name = "code")
    private String code;
    @Column(name = "blocked")
    private Integer blocked = 0;
    @Column(name = "roleValue")
    private Long roleValue;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(name = "permissions_roles",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<PermissionEntity> permissionEntities = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "roles")
    @JsonIgnore
    private List<UserEntity> listUsers = new ArrayList<>();
}
