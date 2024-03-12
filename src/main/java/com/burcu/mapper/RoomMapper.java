package com.burcu.mapper;

import com.burcu.domain.Room;
import com.burcu.dto.request.RoomSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    RoomMapper INSTANCE= Mappers.getMapper(RoomMapper.class);

    Room fromRoomSaveRequestDtoToRoom(final RoomSaveRequestDto dto);
}
