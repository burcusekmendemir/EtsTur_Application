package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TagsRepository extends MongoRepository<Tags, String> {
    List<Tags> findByIdContaining(List<String> tagsList);

    Optional<Tags> findByName(String name);
}
