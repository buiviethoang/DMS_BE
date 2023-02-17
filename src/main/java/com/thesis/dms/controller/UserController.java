package com.thesis.dms.controller;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.auth.RegisterAdminDTO;
import com.thesis.dms.dto.user.UserInsertDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.entity.user.UserEntity;
import com.thesis.dms.security.SecurityCheck;
import com.thesis.dms.service.user.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = IApiName.USER)
@Slf4j
public class UserController extends BaseController{
    @Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private SecurityCheck securityCheck;
    /**
     * Dang ky tai khoan admin
     *
     * @param object {}
     * @return
     */
    @PostMapping(path = "/registerAdmin")
    public ResponseEntity<?> register(@RequestBody RegisterAdminDTO object) {
        try {
            return response(new ResultEntity(1, "register admin successfully", userService.registerAdminUser(object)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
    /**
     * Them moi nguoi dung
     *
     * @param userInsertDTO
     * @return
     */
    @ApiOperation(value = "Create a new user by admin", notes = "{fsdad}")
    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody UserInsertDTO userInsertDTO) {
        try {
            UserEntity user = userService.create(userInsertDTO);
//            userService.applyPermission(user, headers);
            return response(new ResultEntity(1, "register user successfully", user));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
