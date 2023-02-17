package com.thesis.dms.security;

import com.thesis.dms.common.enums.UserType;
import com.thesis.dms.entity.UserDetailsImpl;
import com.thesis.dms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityCheck {
    @Autowired
    JwtUtils jwtUtils;

    public boolean check(Long id, Authentication authentication) {
        if(id == null)
            return false;
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        if(id.equals(userDetails.getId()) || userDetails.getRoleValue() == UserType.ADMIN.getValue())
            return true;
        return false;
    }
    public boolean checkAdmin(Authentication authentication){
        if(authentication != null){
            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
            return userDetails.getRoleValue() == UserType.ADMIN.getValue();
        }
        return false;
    }
}
