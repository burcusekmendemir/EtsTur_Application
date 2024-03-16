package com.burcu.service;

import com.burcu.domain.Otel;
import com.burcu.domain.UserProfile;
import com.burcu.dto.request.UserProfileUpdateRequestDto;
import com.burcu.dto.response.FindByTokenResponseDto;
import com.burcu.dto.response.UserProfileResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.UserProfileMapper;
import com.burcu.repository.UserProfileRepository;
import com.burcu.utility.JwtTokenManager;
import com.burcu.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final UserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;
    private final OtelService otelService;
    private final CacheManager cacheManager;
    public UserProfileService(UserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager, OtelService otelService, CacheManager cacheManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.otelService = otelService;
        this.cacheManager = cacheManager;
    }

    public FindByTokenResponseDto findByToken(String token) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }

        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId.get());
        if(userProfile.isEmpty()){
            throw new OtelException(ErrorType.USER_NOT_FOUND);
        }
        UserProfile user = userProfile.get();
        return  UserProfileMapper.INSTANCE.fromUserProfileToFindByTokenResponseDto(user);
    }


    public Boolean addFavourite(String token, String otelId) {
        Optional<Otel> otel=otelService.findById(otelId);
        if(otel.isEmpty()){
            throw new OtelException(ErrorType.OTEL_NOT_FOUND);
        }
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId.get());
        if(userProfile.isEmpty()){
            throw new OtelException(ErrorType.USER_NOT_FOUND);
        }
        UserProfile user = userProfile.get();
        if(user.getFavOtels().contains(otelId)){
            throw new OtelException(ErrorType.OTEL_IS_FAVOURITE);
        }

        user.getFavOtels().add(otelId);
        userProfileRepository.save(user);
        Objects.requireNonNull(cacheManager.getCache("get-fav-otels")).evict(user);
        return true;
    }


    public UserProfileResponseDto updateUserProfile(UserProfileUpdateRequestDto dto) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authId.isEmpty()){
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId.get());
        if(userProfile.isEmpty()){
            throw new OtelException(ErrorType.USER_NOT_FOUND);
        }
        UserProfile user = userProfile.get();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        userProfileRepository.save(user);
        Objects.requireNonNull(cacheManager.getCache("find-all-user-profile")).evict(user);
        return UserProfileMapper.INSTANCE.fromUserProfileToUserProfileResponseDto(user);
    }


    @Cacheable(value = "get-fav-otels")
    public List<Otel> getFavOtels(String token) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId.get());
        if(userProfile.isEmpty()){
            throw new OtelException(ErrorType.USER_NOT_FOUND);
        }
        return otelService.findAllByOtelIdIn(userProfile.get().getFavOtels());
    }


    @Cacheable(value = "find-all-user-profile")
    public List<UserProfile> findAllByRedis() {
        return userProfileRepository.findAll();
    }


}
