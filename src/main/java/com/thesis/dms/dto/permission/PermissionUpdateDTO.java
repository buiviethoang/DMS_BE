package com.thesis.dms.dto.permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionUpdateDTO {
    private Long id;

    private String name;

    private int value;

    /**
     * Loai permission
     * Theo Role: 1
     * Khong theo Role :0
     */
    private int type;
}
