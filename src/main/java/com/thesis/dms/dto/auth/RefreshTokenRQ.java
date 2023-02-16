package com.thesis.dms.dto.auth;

import javax.validation.constraints.NotBlank;

public class RefreshTokenRQ {
    @NotBlank
    public String refreshToken;
}
