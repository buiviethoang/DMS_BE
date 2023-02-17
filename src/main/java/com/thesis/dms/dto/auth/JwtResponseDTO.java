package com.thesis.dms.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Integer role;
    private String avatar;

    public JwtResponseDTO(String accessToken, String refreshToken, Long id, String username, String email,
                          Integer role,
                          String avatar) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
    }
}
