package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PropertiesRepository extends MongoRepository<Properties, String> {
    Optional<Properties> findByName(String name);

    List<Properties> findByParentId(String id);
}
