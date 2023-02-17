package com.thesis.dms.service.role;

import com.thesis.dms.dto.GetAllElementResponse;
import com.thesis.dms.dto.ReturnPaginationDTO;
import com.thesis.dms.dto.role.RoleInsertDTO;
import com.thesis.dms.dto.role.RoleResponseDTO;
import com.thesis.dms.entity.auth.RoleEntity;
import com.thesis.dms.exception.CustomException;

import java.util.List;

public interface IRoleService {
    RoleEntity create(RoleInsertDTO role) throws CustomException;
    RoleEntity update(RoleInsertDTO newRole, Long id) throws CustomException;
    void delete(Long id);
    RoleEntity getDetail(Long id);
    ReturnPaginationDTO<RoleEntity> search(Integer page, Integer size, String stringQuery, Long value, int type, int blocked, Integer userType);

    List<GetAllElementResponse> findAll();

    ReturnPaginationDTO<RoleResponseDTO> searchV2(
            Integer page,
            Integer size,
            String name,
            String code,
            Long value,
            int sortType,
            int blocked,
            Integer userType
    );
}
