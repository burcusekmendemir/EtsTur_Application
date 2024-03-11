package com.burcu.service;

import com.burcu.domain.UserProfile;
import com.burcu.repository.UserProfileRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service

public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }
}
