package com.burcu.controller;

import com.burcu.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.burcu.constants.RestApiUrls.*;

import static com.burcu.constants.RestApiUrls.USER_PROFILE;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserProfileController {

    private final UserProfileService userProfileService;

}
