package com.thesis.dms.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    public String username;
    public String password;
}
