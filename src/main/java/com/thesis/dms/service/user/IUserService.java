package com.thesis.dms.service.user;

import com.thesis.dms.dto.ForgetPasswordDTO;
import com.thesis.dms.dto.RegisterDTO;
import com.thesis.dms.dto.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.exception.CustomException;

public interface IUserService {
    UserEntity create(UserDTO object) throws CustomException;
    UserEntity updatePassword(Long id, UpdatePasswordDTO object) throws CustomException;
    void forgetPassword(ForgetPasswordDTO object) throws CustomException;
    void resetPassword(Long id);
    UserEntity register(RegisterDTO object, int userMod) throws Exception;

    UserEntity getUserById(Long id);
}
