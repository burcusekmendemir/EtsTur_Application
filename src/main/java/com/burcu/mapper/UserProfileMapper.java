package com.burcu.mapper;

import com.burcu.domain.UserProfile;
import com.burcu.dto.response.FindByTokenResponseDto;
import com.burcu.dto.response.UserProfileResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {

    UserProfileMapper INSTANCE= Mappers.getMapper(UserProfileMapper.class);

    UserProfileResponseDto fromUserProfileToUserProfileResponseDto(final UserProfile userProfile);

    FindByTokenResponseDto fromUserProfileToFindByTokenResponseDto(final UserProfile user);
}
