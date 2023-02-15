package com.thesis.dms.controller;

import com.thesis.dms.config.AuthEntryPointJwt;
import com.thesis.dms.constant.request.IApiName;
import com.thesis.dms.dto.RegisterDTO;
import com.thesis.dms.dto.user.UserDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.security.SecurityCheck;
import com.thesis.dms.service.user.IUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = IApiName.USER)
public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private SecurityCheck securityCheck;
    /**
     * Dang ky tai khoan
     *
     * @param object {}
     * @return
     */
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO object) {
        try {
            return response(new ResultEntity(1, "register successfully", userService.register(object)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

//    /**
//     * Them moi nguoi dung
//     *
//     * @param UserDTO
//     * @return
//     */
//    @ApiOperation(value = "Create a new user by admin", notes = "{\n" +
//            "  \"roleId\": [\n" +
//            "    1,2\n" +
//            "  ],\n" +
//            "  \"departmentId\": 1,\n" +
//            "  \"countryId\": 1,\n" +
//            "  \"zoneId\": 1,\n" +
//            "  \"districtId\": 1,\n" +
//            "  \"provinceId\": 1,\n" +
//            "  \"user\": {\n" +
//            "    \"email\": \"cdmn1994@gmail.com\",\n" +
//            "    \"gender\": 0,\n" +
//            "    \"name\": \"Nguyễn Thục Trang\",\n" +
//            "    \"phone\": \"0972628256\",\n" +
//            "    \"verify\": 0\n" +
//            "  }\n" +
//            "}")
//    @PostMapping(path = "/create")
//    public ResponseEntity<?> create(@RequestBody UserDTO userDTO, @RequestHeader Map<String, Object> headers) {
//        try {
//            UserEntity user = userService.create(userDTO);
//            userService.applyPermission(user, headers);
//            return response(new ResultEntity(1, "register user successfully", user));
//        } catch (Exception ex) {
//            return response(error(ex));
//        }
//    }
}
