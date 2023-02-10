package com.thesis.dms.dto;

import javax.validation.constraints.NotBlank;

public class RefreshTokenRQ {
    @NotBlank
    public String refreshToken;
}
