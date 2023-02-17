package com.thesis.dms.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.dms.entity.BaseEntity;
import com.thesis.dms.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class PermissionEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "apply_user_type")
    private Integer applyUserType;
    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "permissions")
    @JsonIgnore
    private List<UserEntity> listUsers = new ArrayList<>();
    /**
     * gia tri role
     *
     */
    private Long value;
}
