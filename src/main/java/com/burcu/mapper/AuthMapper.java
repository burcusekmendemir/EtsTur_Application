package com.burcu.mapper;

import com.burcu.domain.Auth;
import com.burcu.dto.request.AuthRegisterRequestDto;
import com.burcu.dto.response.RegisterResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    Auth fromAuthRegisterRequestDtoToAuth(final AuthRegisterRequestDto dto);

    RegisterResponseDto fromAuthToRegisterResponseDto(final Auth auth);
}
