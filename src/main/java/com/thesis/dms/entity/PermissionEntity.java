package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "guard_name")
    private String guardName;

    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "permissions")
    @JsonIgnore
    private List<UserEntity> listUsers = new ArrayList<>();

}
