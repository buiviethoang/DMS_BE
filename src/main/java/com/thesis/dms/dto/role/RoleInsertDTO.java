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
    private RoleEntity role;
    private List<Long> permissions;
}
