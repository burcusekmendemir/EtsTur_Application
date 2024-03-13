package com.burcu.repository;


import com.burcu.domain.Otel_Room;
import com.burcu.domain.Room_Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomImageRepository extends MongoRepository<Room_Image, String> {


}
