package com.thesis.dms.service.permission;

import com.thesis.dms.common.response.IMapData;
import com.thesis.dms.dto.permission.PermissionRequestDTO;
import com.thesis.dms.entity.PermissionEntity;
import com.thesis.dms.repository.PermissionRepository;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Service
public class PermissionServiceImpl extends BaseService implements IPermissionService, IMapData<PermissionEntity> {

    @Autowired
    PermissionRepository permissionRepository;

    @Transactional
    public PermissionEntity save(PermissionEntity entity) {
        return permissionRepository.save(entity);
    }

    @Override
    public Map<String, Object> getMapData(PermissionEntity entity) {
        return null;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public PermissionEntity create(PermissionRequestDTO permissionRequestDTO) {
        PermissionEntity newPermission = new PermissionEntity();
        newPermission.setName(permissionRequestDTO.getName());
        newPermission.setDescription(permissionRequestDTO.getDescription());
        newPermission.setApplyUserType(permissionRequestDTO.getApplyUserType());
        newPermission.setValue(permissionRequestDTO.getValue());
        save(newPermission);
        return newPermission;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean delete(long id) {
        try{
            permissionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public PermissionEntity update(PermissionEntity object, long id) {
//        PermissionEntity entity = getEntityById(permissionRepository, id);
//
//        entity.setName(object.getName());
//        entity.setApplyUserType(object.getApplyUserType());
//        return permissionRepository.save(entity);
//    }
//
//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public Boolean delete(long id) {
//        try{
//            permissionRepository.deleteById(id);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }

//    @Override
//    public Page<PermissionEntity> search(Integer page, Integer size, String name, Integer userType) throws CustomException {
////        UserEntity user = getCurrentUser();
////        if (user.getRole() != 4) {
////            throw getException(13, "User khong co quyen thao tac nay");
////        }
//    	if (userType != null && userType == UserType.AGENT.getValue()) {
//    		return permissionRepository.searchByUserType(name, userType, getDefaultPage(page, size));
//    	} else
//    		return permissionRepository.search(name, getDefaultPage(page, size));
//    }
//
    public PermissionEntity getPermission(Long id) {
        return getEntityById(permissionRepository, id);
    }
//
//    @Override
//    public List<PermissionEntity> findAll() {
//        return permissionRepository.findAll();
//    }
//
//    @Override
//    public List<PermissionUpdateDTO> findPermissionForUser(Long id) throws CustomException {
//        if(id == null){
//            throw getException(2,"Id user không được để trống");
//        }
//        UserEntity userEntity = userRepository.findByIdUser(id);
//        if(userEntity == null){
//            throw getException(3,"Không tồn tại user");
//        }
//        List<PermissionUpdateDTO> listResult = new ArrayList<>();
//        for(RolesEntity rolesEntity : userEntity.getRoles()){
//            for(PermissionEntity permissionEntity : rolesEntity.getPermissionEntities()){
//                PermissionUpdateDTO permissionUpdateDTO = new PermissionUpdateDTO();
//                permissionUpdateDTO.setId(permissionEntity.getId());
//                permissionUpdateDTO.setName(permissionEntity.getName());
//                permissionUpdateDTO.setValue(permissionEntity.getValue().intValue());
//                permissionUpdateDTO.setType(1);
//                listResult.add(permissionUpdateDTO);
//            }
//        }
//        for(PermissionEntity permissionEntity : userEntity.getPermissions()){
//            PermissionUpdateDTO permissionUpdateDTO = new PermissionUpdateDTO();
//            permissionUpdateDTO.setId(permissionEntity.getId());
//            permissionUpdateDTO.setName(permissionEntity.getName());
//            permissionUpdateDTO.setValue(permissionEntity.getValue().intValue());
//            permissionUpdateDTO.setType(0);
//            listResult.add(permissionUpdateDTO);
//        }
//        return listResult;
//    }
}
