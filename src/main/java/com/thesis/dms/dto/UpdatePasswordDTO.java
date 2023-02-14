package com.thesis.dms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
    public String passwordOld;
    public String passwordNew;
}
