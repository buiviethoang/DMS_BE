package com.thesis.dms.service.user;

import com.thesis.dms.common.enums.UserType;
import com.thesis.dms.common.response.IMapData;
import com.thesis.dms.dto.auth.ForgetPasswordDTO;
import com.thesis.dms.dto.auth.RegisterAdminDTO;
import com.thesis.dms.dto.auth.UpdatePasswordDTO;
import com.thesis.dms.dto.user.UserInsertDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.entity.auth.TokenPasswordEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.TokenPasswordRepository;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.utils.JwtUtils;
import com.thesis.dms.utils.RandomUtils;
import com.thesis.dms.utils.StrUtils;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements IUserService, IMapData<UserEntity> {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenPasswordRepository tokenPasswordRepository;

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
    public UserEntity create(UserInsertDTO object) throws CustomException {
        UserEntity user = initCreation(object);
        for (Long roleId : object.getRoleIds()) {
            RolesEntity rolesEntity = rolesService.getDetail(roleId);
            if (rolesEntity != null) {
                user.getRoles().add(rolesEntity);
            }
        }
        user.setDepartmentCodes(object.getDepartmentCodes());
        user.setPosition(object.getPosition());
        save(user);
        if (object.getAgencyType() != null && object.getAgencyType() == AgencyType.ATHLETE.getValue()) {
            user.setUserCode(formatCode(user.getId(), "VDV"));
        } else {
            user.setUserCode(formatCode(user.getId(), "NV"));
        }
        return save(user);
    }

    private UserEntity initCreation(UserInsertDTO object) throws CustomException {

        UserEntity user = customDozerBeanMapper.map(object, UserEntity.class);
        if (!StringUtils.isEmpty(user.getPhone())) {
            if (userRepository.findByPhone(user.getPhone()) != null) {
                throw getException(2, "Số điện thoại đã tồn tại!");
            }
        }
        // Neu co gui thong tin email, ktra xem email co bi trung khong
        if (!StringUtils.isEmpty(user.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw getException(3, "Email đã tồn tại!");
            }
        }

        if (StringHelper.isEmpty(user.getUsername())) {
            String username = createUsername(user.getUsername());
            user.setUsername(username);
        }

        user.setPassword(passwordEncoder.encode(object.getPassword()));

        user = save(user);

        UserEntity userCreate = getCurrentUser();
        if (userCreate != null) {
            user.setUserCreate(userCreate);
            user.setUserUpdate(userCreate);
        }

        String token = RandomUtils.getRandomId();
        saveTokenPassword(user, token);

        return save(user);
    }

    private String createUsername(String name) {
        int index = 0;
        String nickName = StrUtils.convertToUnsignedLowerCase(name).replace(" ", "");
        while (userRepository.findByUsername(nickName + (index == 0 ? "" : "" + index)) != null) {
            index++;
        }
        return nickName + (index == 0 ? "" : "" + index);
    }
    private void saveTokenPassword(UserEntity user, String token) {
        TokenPasswordEntity tokenPasswordEntity = new TokenPasswordEntity();
        tokenPasswordEntity.setExpiredDate(LocalDateTime.now().plusDays(3));
        tokenPasswordEntity.setToken(token);
        tokenPasswordEntity.setUser(user);
        tokenPasswordRepository.save(tokenPasswordEntity);
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
    public UserEntity registerAdminUser(RegisterAdminDTO registeringUser) throws CustomException {
        UserEntity newUser = new UserEntity();
        if (!StringUtils.isEmpty(registeringUser.getEmail())) {
            UserEntity checkEmail = userRepository.findByEmail(registeringUser.getEmail());

            if (checkEmail != null) {
                throw getException(6, "Email đã được sử dụng trong hệ thống. Vui lòng nhập lại.");
            }
        }

        if (!StringUtils.isEmpty(registeringUser.getPhone())) {
            UserEntity checkPhone = userRepository.findByPhone(registeringUser.getPhone());
            if (checkPhone != null) {
                throw getException(2, "Số điện thoại đã tồn tại");
            }
        }
        newUser.setAvatar(registeringUser.getAvatar());
        newUser.setEmail(registeringUser.getEmail());
        newUser.setUsername(registeringUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(registeringUser.getPassword()));
        newUser.setPhone(registeringUser.getPhone());
        newUser.setRole(UserType.ADMIN.getValue());
        save(newUser);
        return newUser;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return null;
    }
}
