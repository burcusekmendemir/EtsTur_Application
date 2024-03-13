package com.burcu.repository;


import com.burcu.domain.Otel_Comment;
import com.burcu.domain.Otel_Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtelImageRepository extends MongoRepository<Otel_Image, String> {


}
