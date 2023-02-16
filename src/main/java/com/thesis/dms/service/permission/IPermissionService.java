package com.thesis.dms.service.permission;

import com.thesis.dms.dto.permission.PermissionRequestDTO;
import com.thesis.dms.entity.PermissionEntity;

public interface IPermissionService {
    PermissionEntity create(PermissionRequestDTO object);

//    PermissionEntity update(PermissionEntity object, long id);
//
    Boolean delete(long id);
//
//    Page<PermissionEntity> search(Integer page, Integer size, String name, Integer userType) throws CustomException;

    PermissionEntity getPermission(Long id);

//    List<PermissionEntity> findAll();
//
//    List<PermissionUpdateDTO> findPermissionForUser(Long id) throws CustomException;
}
