package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Otel_Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OtelTagsRepository extends MongoRepository<Otel_Tags, String> {
    List<Otel_Tags> findByOtelId(String id);
}
