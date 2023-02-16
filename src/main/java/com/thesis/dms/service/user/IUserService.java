package com.thesis.dms.service.user;

import com.thesis.dms.dto.auth.ForgetPasswordDTO;
import com.thesis.dms.dto.auth.RegisterAdminDTO;
import com.thesis.dms.dto.auth.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserDTO;
import com.thesis.dms.dto.user.UserInsertDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.exception.CustomException;

public interface IUserService {
    UserEntity create(UserInsertDTO object) throws CustomException;
    UserEntity updatePassword(Long id, UpdatePasswordDTO object) throws CustomException;
    void forgetPassword(ForgetPasswordDTO object) throws CustomException;
    void resetPassword(Long id);
    UserEntity registerAdminUser(RegisterAdminDTO object) throws Exception;

    UserEntity getUserById(Long id);
}
