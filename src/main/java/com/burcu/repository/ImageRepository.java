package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> findByName(String name);
}
