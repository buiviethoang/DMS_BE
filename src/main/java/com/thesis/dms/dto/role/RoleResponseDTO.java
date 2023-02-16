package com.thesis.dms.dto.role;

import com.thesis.dms.dto.BaseReturnDTO;
import com.thesis.dms.dto.permission.PermissionResponseDTO;
import com.thesis.dms.entity.PermissionEntity;
import com.thesis.dms.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDTO extends BaseReturnDTO {
    private String name;
    private String code;
    private Integer blocked;

    private Set<PermissionResponseDTO> permissions = new HashSet<>();
    public RoleResponseDTO(RoleEntity roleEntity) {
        this.setId(roleEntity.getId());
        this.setCreatedDate(roleEntity.getCreatedDate());
        this.setUpdatedDate(roleEntity.getUpdatedDate());
        this.name = roleEntity.getName();
        this.code = roleEntity.getCode();
        this.blocked = roleEntity.getBlocked();
        for (PermissionEntity permissionEntity : roleEntity.getPermissionEntities()) {
            this.permissions.add(new PermissionResponseDTO(permissionEntity));
        }
    }
}
