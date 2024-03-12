package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findByRoomNumber(int roomNumber);
}
