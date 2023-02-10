package com.thesis.dms.service.auth;

import com.thesis.dms.dto.LoginRequestDTO;
import com.thesis.dms.dto.RefreshTokenRQ;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.exception.CustomException;
import org.springframework.security.core.Authentication;

public interface IAuthService {
    ResultEntity authenticateUser(LoginRequestDTO loginRequestDTO/*,int pageType*/) throws CustomException;

    void logoutUser(Authentication authentication);

    void logoutByUsername(String username, Authentication authentication) throws CustomException;

    ResultEntity refreshToken(RefreshTokenRQ request);
}
