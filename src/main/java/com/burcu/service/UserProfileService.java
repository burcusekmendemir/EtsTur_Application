package com.burcu.service;

import com.burcu.domain.Otel;
import com.burcu.domain.UserProfile;
import com.burcu.dto.request.UserProfileUpdateRequestDto;
import com.burcu.dto.response.UserProfileResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.UserProfileMapper;
import com.burcu.repository.UserProfileRepository;
import com.burcu.utility.JwtTokenManager;
import com.burcu.utility.ServiceManager;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final UserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;
    private final OtelService otelService;

    public UserProfileService(UserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager, OtelService otelService) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.otelService = otelService;
    }

    public UserProfile findByToken(String token) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
        return userProfileRepository.findByAuthId(authId.get()).get();
    }

    public Boolean addFavourite(String token, String otelId) {
        UserProfile userProfile=findByToken(token);
        userProfile.getFavOtels().add(otelId);
        userProfileRepository.save(userProfile);
        return true;
    }

    public UserProfileResponseDto findUserDtoByToken(String token) {
        UserProfile user = findByToken(token);
        return UserProfileResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .password(user.getPassword())
                .favOtels(user.getFavOtels())
                .build();
    }

    public UserProfileResponseDto updateUserProfile(UserProfileUpdateRequestDto dto) {
        UserProfile user=findByToken(dto.getToken());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        userProfileRepository.save(user);
        return UserProfileMapper.INSTANCE.fromUserProfileToUserProfileResponseDto(user);
    }

    public List<Otel> getFavOtels(String token) {
        UserProfile userProfile = findByToken(token);
        return otelService.findAllByOtelIdIn(userProfile.getFavOtels());
    }
}
