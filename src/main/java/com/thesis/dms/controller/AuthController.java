package com.thesis.dms.controller;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.auth.LoginRequestDTO;
import com.thesis.dms.dto.auth.RefreshTokenRQ;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.auth.IAuthService;
import com.thesis.dms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IApiName.AUTHENTICATION)
public class AuthController extends BaseController{
    @Autowired
    IAuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        try {
            return ResponseEntity.ok(authService.authenticateUser(loginRequest));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @GetMapping("logout")
    public ResponseEntity<?> logout(Authentication authentication) {
        try {
            authService.logoutUser(authentication);
            return response(new ResultEntity(1, "Logout successfully!", true));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @GetMapping("remote-logout")
    public ResponseEntity<?> remoteLogout(String username, Authentication authentication) {
        try {
            authService.logoutByUsername(username, authentication);
            return response(new ResultEntity(1, "Logout remote successfully!", true));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @GetMapping("refresh")
    public ResponseEntity<?> refresh(RefreshTokenRQ request) {
        try {
            return ResponseEntity.ok(authService.refreshToken(request));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
