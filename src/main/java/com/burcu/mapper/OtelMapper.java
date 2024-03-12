package com.burcu.mapper;

import com.burcu.domain.Otel;
import com.burcu.dto.request.OtelSaveRequestDto;
import com.burcu.dto.response.OtelSaveResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OtelMapper {

    OtelMapper INSTANCE= Mappers.getMapper(OtelMapper.class);

    Otel fromOtelSaveRequestDtoToOtel(final OtelSaveRequestDto dto);

    OtelSaveResponseDto fromOtelToOtelSaveResponseDto(final Otel otel);
}
