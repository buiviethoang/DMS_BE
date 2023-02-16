package com.thesis.dms.dto.permission;

import com.thesis.dms.entity.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDTO {
    private Long id;
    private String name;
    private Long value;
    /**
     * Loai permission
     * Theo Role: 1
     * Khong theo Role :0
     */
    private Integer type;
    public PermissionResponseDTO(PermissionEntity permissionEntity) {
        this.id = permissionEntity.getId();
        this.name = permissionEntity.getName();
        this.value = permissionEntity.getValue();
        this.type = permissionEntity.getApplyUserType();
    }
}
