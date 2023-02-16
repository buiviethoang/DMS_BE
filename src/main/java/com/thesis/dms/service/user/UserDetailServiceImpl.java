package com.thesis.dms.service.user;

import com.thesis.dms.entity.UserDetailsImpl;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);
        return UserDetailsImpl.build(user);
    }
}
