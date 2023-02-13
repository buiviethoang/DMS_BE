package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission")
@Getter
@Setter
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
    /**
     * gia tri role
     *
     */
    private Long value;
}
