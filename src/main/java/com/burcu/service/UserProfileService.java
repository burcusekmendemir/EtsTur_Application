package com.burcu.service;

import com.burcu.domain.UserProfile;
import com.burcu.repository.UserProfileRepository;
import com.burcu.utility.JwtTokenManager;
import com.burcu.utility.ServiceManager;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service

public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final UserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserProfileService(UserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public User findByToken(String token) {
        return null;
    }
}
