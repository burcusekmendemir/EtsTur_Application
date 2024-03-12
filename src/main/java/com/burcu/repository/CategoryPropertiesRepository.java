package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Category_Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryPropertiesRepository extends MongoRepository<Category_Properties, String> {
    Optional<Category_Properties> findByCategoryIdAndPropertiesId(String categoryId, String propertiesId);
}
