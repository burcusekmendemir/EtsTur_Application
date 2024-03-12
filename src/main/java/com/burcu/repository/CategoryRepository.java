package com.burcu.repository;

import com.burcu.domain.Address;
import com.burcu.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {


    Optional<Category> findByName(String name);
}
