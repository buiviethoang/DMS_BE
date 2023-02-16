package com.thesis.dms.dto.permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequestDTO {
    private String name;
    private String description;
    private Long value;
    /**
     * Loai permission
     * Theo Role: 1
     * Khong theo Role :0
     */
    private Integer applyUserType;
}
