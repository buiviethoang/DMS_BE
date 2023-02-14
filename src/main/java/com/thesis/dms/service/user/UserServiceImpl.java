package com.thesis.dms.service.user;

import com.thesis.dms.common.enums.UserType;
import com.thesis.dms.constant.response.IMapData;
import com.thesis.dms.dto.ForgetPasswordDTO;
import com.thesis.dms.dto.RegisterDTO;
import com.thesis.dms.dto.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public class UserServiceImpl extends BaseService implements IUserService, IMapData<UserEntity> {

    @Autowired
    UserRepository userRepository;
    @Override
    public Map<String, Object> getMapData(UserEntity entity) {
        return null;
    }

    @Transactional
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity create(UserDTO object) throws CustomException {
        return null;
    }

    @Override
    public UserEntity updatePassword(Long id, UpdatePasswordDTO object) throws CustomException {
        return null;
    }

    @Override
    public void forgetPassword(ForgetPasswordDTO object) throws CustomException {

    }

    @Override
    public void resetPassword(Long id) {

    }

    @Override
    public UserEntity register(RegisterDTO registeringUser, int userMod) throws CustomException {

        return null;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return null;
    }
}
