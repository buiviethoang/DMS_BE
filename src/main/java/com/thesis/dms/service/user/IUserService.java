package com.thesis.dms.service.user;

import com.thesis.dms.dto.auth.ForgetPasswordDTO;
import com.thesis.dms.dto.auth.RegisterAdminDTO;
import com.thesis.dms.dto.auth.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserInsertDTO;
import com.thesis.dms.entity.user.UserEntity;
import com.thesis.dms.exception.CustomException;

import java.util.Map;

public interface IUserService {
    UserEntity registerAdminUser(RegisterAdminDTO object) throws Exception;
    UserEntity create(UserInsertDTO object) throws CustomException;
    UserEntity updatePassword(Long id, UpdatePasswordDTO object) throws CustomException;
    void forgetPassword(ForgetPasswordDTO object) throws CustomException;
    void resetPassword(Long id);
    UserEntity getUserById(Long id);

    void applyPermission(UserEntity user, Map<String, Object> headers);
}
