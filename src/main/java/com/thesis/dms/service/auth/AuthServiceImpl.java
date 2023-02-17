package com.thesis.dms.service.auth;

import com.thesis.dms.dto.auth.LoginRequestDTO;
import com.thesis.dms.dto.auth.RefreshTokenRQ;
import com.thesis.dms.dto.auth.JwtResponseDTO;
import com.thesis.dms.entity.auth.RefreshTokenEntity;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.entity.user.UserDetailsImpl;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.RefreshTokenRepository;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.utils.JedisUtil;
import com.thesis.dms.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl extends BaseService implements IAuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResultEntity authenticateUser(LoginRequestDTO loginRequest) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception notFound) {
            notFound.printStackTrace();
            throw caughtException(2, "Thông tin tài khoản mật khẩu không chính xác!");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        if (userDetails.getActive() == 1) {
//            throw getException(2, "User Not Active");
//        }
//        template.opsForValue().set(userDetails.getUsername(), jwt);
//        logger.info("token data: {}", template.opsForValue().get(userDetails.getUsername()));

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new ResultEntity(1, "login successfully", new JwtResponseDTO(jwt, "",
               userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getRoleValue(),
                userDetails.getAvatar()
        ));
    }

    @Override
    public void logoutUser(Authentication authentication) {
        if (authentication != null) {
//            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
//            template.delete(userDetails.getUsername());
        }
    }

    @Override
    public void logoutByUsername(String username, Authentication authentication) throws CustomException {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        if (userDetails.getRole() != UserType.ADMIN.getValue()) {
//            throw new CustomException(401, "Không đủ quyền");
//        }
//        logger.info("username delete token redis: {}", template.opsForValue().get(username));

//        template.delete(username);
    }

    @Override
    public ResultEntity refreshToken(RefreshTokenRQ request) {
        return null;
    }

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenEntity createRefreshToken(Long userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(86400000));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) throws CustomException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
