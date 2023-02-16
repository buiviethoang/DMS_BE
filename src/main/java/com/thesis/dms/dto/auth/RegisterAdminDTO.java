package com.thesis.dms.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterAdminDTO {
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String phone;
}
