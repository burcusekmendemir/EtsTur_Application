package com.burcu.repository;


import com.burcu.domain.Otel_Image;
import com.burcu.domain.Otel_Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtelRoomRepository extends MongoRepository<Otel_Room, String> {


}
