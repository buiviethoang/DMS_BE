package com.thesis.dms.dto.role;

import com.thesis.dms.entity.auth.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class RoleInsertDTO {
    @NotNull
    private String name;
    private String description;
    private Integer applyUserType;
    private String code;
    private Integer blocked;
    private Long roleValue;w
    private List<Long> permissions;
}
