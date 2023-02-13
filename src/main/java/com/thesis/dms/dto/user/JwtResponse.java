package com.thesis.dms.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Integer role;
    private Integer level;
    private List<String> roles;
    private String avatar;
    private String userCode;
    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email,
                       Integer role, List<String> roles,
                       String avatar, String userCode, Integer level) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.roles = roles;
        this.avatar = avatar;
        this.userCode = userCode;
        this.level = level;
    }
}
