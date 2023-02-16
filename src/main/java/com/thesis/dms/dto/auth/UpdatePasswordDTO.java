package com.thesis.dms.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
    public String passwordOld;
    public String passwordNew;
}
