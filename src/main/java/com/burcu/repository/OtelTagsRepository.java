package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Otel_Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtelTagsRepository extends MongoRepository<Otel_Tags, String> {
}
