package com.thesis.dms.controller;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.permission.PermissionRequestDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.permission.IPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IApiName.PERMISSION)
public class PermissionController extends BaseController{
    @Autowired
    IPermissionService permissionService;

    @ApiOperation(value = "Create new permission")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PermissionRequestDTO permission) {
        try {
            return response(new ResultEntity(1, "Create permission successfully", permissionService.create(permission)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation(value = "Delete a specific permission")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(Long id) {
        try {
            return response(new ResultEntity(1, "Delete permission successfully", permissionService.delete(id)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
