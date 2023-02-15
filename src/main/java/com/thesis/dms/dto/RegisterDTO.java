package com.thesis.dms.dto;

import com.thesis.dms.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String phone;
}
