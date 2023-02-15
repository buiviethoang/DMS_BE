package com.thesis.dms.service.user;

import com.thesis.dms.constant.response.IMapData;
import com.thesis.dms.dto.ForgetPasswordDTO;
import com.thesis.dms.dto.RegisterDTO;
import com.thesis.dms.dto.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements IUserService, IMapData<UserEntity> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;
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
    public UserEntity register(RegisterDTO registeringUser) throws CustomException {
        UserEntity newUser = new UserEntity();
        if (!StringUtils.isEmpty(registeringUser.getEmail())) {
            UserEntity checkEmail = userRepository.findByEmail(registeringUser.getEmail());

            if (checkEmail != null) {
                throw getException(6, "Email đã được sử dụng trong hệ thống. Vui lòng nhập lại.");
            }
        }

//        if (!StringUtils.isEmpty(entity.getProfileEmployee().getPhone())) {
//            UserEntity checkPhone = userRepository.findByPhone(entity.getRole(), entity.getProfileEmployee().getPhone());
//            if (checkPhone != null) {
//                throw getException(2, "Số điện thoại đã tồn tại");
//            }
//        }
        newUser.setAvatar(registeringUser.getAvatar());
        newUser.setEmail(registeringUser.getEmail());
        newUser.setUsername(registeringUser.getUsername());
        newUser.setPassword(registeringUser.getPassword());
        newUser.setPhone(registeringUser.getPhone());
        newUser.setLoginToken(jwtUtils.generateJwtToken(newUser));
        save(newUser);
        return newUser;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return null;
    }
}
