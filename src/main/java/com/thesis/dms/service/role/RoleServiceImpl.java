package com.thesis.dms.service.role;

import com.thesis.dms.common.response.IMapData;
import com.thesis.dms.dto.GetAllElementResponse;
import com.thesis.dms.dto.ReturnPaginationDTO;
import com.thesis.dms.dto.role.RoleInsertDTO;
import com.thesis.dms.dto.role.RoleResponseDTO;
import com.thesis.dms.entity.auth.PermissionEntity;
import com.thesis.dms.entity.auth.RoleEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.RolesRepository;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.service.permission.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl extends BaseService implements IRoleService, IMapData<RoleEntity> {
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    IPermissionService permissionService;
    @Override
    public Map<String, Object> getMapData(RoleEntity entity) {
        return null;
    }

    @Override
    @Transactional // if not, throw error: Detached Entity Passed to Persist
    public RoleEntity create(RoleInsertDTO role) throws CustomException {
        try {
            RoleEntity newRole = customDozerBeanMapper.map(role, RoleEntity.class);
            RoleEntity checkRole = rolesRepository.checkRoleCode(role.getCode());
            if (checkRole != null) {
                throw caughtException(2, "Mã vai trò đã tồn tại!");
            }
            if (rolesRepository.checkRoleName(role.getName()) != null) {
                throw caughtException(2, "Tên vai trò đã tồn tại!");
            }
            List<Long> permissionIds = role.getPermissions();
            if(permissionIds != null) {
                for (Long permissionId : permissionIds) {
                    PermissionEntity permissionEntity = permissionService.getPermission(permissionId);
                    if (permissionEntity != null) {
                        newRole.getPermissionEntities().add(permissionEntity);
                    }
                }
            }
            log.info("Creating role {} ", newRole.getName());
            return rolesRepository.save(newRole);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw caughtException(2, "Lỗi! Vui lòng thử lại!");
        }
    }

    @Override
    public RoleEntity update(RoleInsertDTO newRole, Long id) throws CustomException {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public RoleEntity getDetail(Long id) {
        return getEntityById(rolesRepository, id);
    }

    @Override
    public ReturnPaginationDTO<RoleEntity> search(Integer page, Integer size, String stringQuery, Long value, int type, int blocked, Integer userType) {
        return null;
    }

    @Override
    public List<GetAllElementResponse> findAll() {
        return null;
    }

    @Override
    public ReturnPaginationDTO<RoleResponseDTO> searchV2(Integer page, Integer size, String name, String code, Long value, int sortType, int blocked, Integer userType) {
        return null;
    }
}
