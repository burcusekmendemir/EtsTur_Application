package com.burcu.controller;

import com.burcu.domain.Otel;
import com.burcu.domain.UserProfile;
import com.burcu.dto.request.UserProfileUpdateRequestDto;
import com.burcu.dto.response.FindByTokenResponseDto;
import com.burcu.dto.response.UserProfileResponseDto;
import com.burcu.service.UserProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.burcu.constants.RestApiUrls.*;

import static com.burcu.constants.RestApiUrls.USER_PROFILE;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserProfileController {


    private final UserProfileService userProfileService;

    @PostMapping(SAVE_USER_PROFILE)
    @CrossOrigin("*")
    public ResponseEntity<UserProfile> saveUserProfile(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.save(userProfile));
    }

    @PostMapping(ADD_FAVOURITE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addFavourite(@RequestParam String token, @RequestParam String otelId) {
        return ResponseEntity.ok(userProfileService.addFavourite(token, otelId));
    }

    @PostMapping(FIND_BY_TOKEN)
    @CrossOrigin("*")
    public ResponseEntity<FindByTokenResponseDto> findByToken(@RequestParam String token) {
        return ResponseEntity.ok(userProfileService.findByToken(token));
    }

    @PostMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<UserProfileResponseDto> updateUserProfile(@RequestBody UserProfileUpdateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(dto));
    }

    @PostMapping(GET_FAV_OTELS)
    @CrossOrigin("*")
    public ResponseEntity<List<Otel>> getFavOtels(@RequestParam String token) {
        return ResponseEntity.ok(userProfileService.getFavOtels(token));
    }
    @PostMapping(FIND_ALL)
    @CrossOrigin("*")
    public ResponseEntity<List<UserProfile>> findAllByRedis() {
        return ResponseEntity.ok(userProfileService.findAllByRedis());
    }


}
