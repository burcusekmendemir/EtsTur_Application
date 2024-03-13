package com.burcu.controller;

import com.burcu.service.UserProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.burcu.constants.RestApiUrls.*;

import static com.burcu.constants.RestApiUrls.USER_PROFILE;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/find-by-token")
    public ResponseEntity<User> findByToken(@RequestParam String token){
        return ResponseEntity.ok(userProfileService.findByToken(token));
    }

}
