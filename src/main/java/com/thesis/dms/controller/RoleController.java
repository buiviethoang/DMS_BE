package com.thesis.dms.controller;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.role.RoleInsertDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.role.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(IApiName.ROLE)
public class RoleController extends BaseController{
    @Autowired
    IRoleService rolesService;

    @ApiOperation(value = "Create a new role")
    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody RoleInsertDTO object) {
        try {
            return response(ResultEntity.returnSuccessResult(rolesService.create(object)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
