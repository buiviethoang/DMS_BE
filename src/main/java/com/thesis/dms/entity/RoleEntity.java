package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "guard_name")
    private String guardName;

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
