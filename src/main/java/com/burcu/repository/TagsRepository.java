package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagsRepository extends MongoRepository<Tags, String> {
}
