package com.burcu.repository;


import com.burcu.domain.Otel_Comment;
import com.burcu.domain.Otel_Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtelPropertiesRepository extends MongoRepository<Otel_Properties, String> {


}
