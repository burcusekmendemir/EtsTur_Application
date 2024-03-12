package com.burcu.service;


import com.burcu.domain.Room;
import com.burcu.dto.request.RoomSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.RoomMapper;
import com.burcu.repository.RoomRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService extends ServiceManager<Room, String> {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        super(roomRepository);
        this.roomRepository = roomRepository;
    }

    public Room save(RoomSaveRequestDto dto) {
        Optional<Room> roomOptional=roomRepository.findByRoomNumber(dto.getRoomNumber());
        if (roomOptional.isPresent()){
            throw new OtelException(ErrorType.ROOM_ALREADY_EXISTS);
        }
        return save(RoomMapper.INSTANCE.fromRoomSaveRequestDtoToRoom(dto));
    }
}

/**
 * TODO:
 * Adres kısmında ECity kısmı
 * Liste olarak enumları elle seçme nasıl olur REquestPAram? yoksa direkt kendimiz mi yazacağız.
 * Category properties kısmında parentid yazıyoruz ancak alt detayı nasıl gözükecek
 *
 */
