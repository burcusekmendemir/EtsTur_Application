package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Otel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtelRepository extends MongoRepository<Otel, String> {
    Optional<Otel> findByNameAndAddressId(String name, String addressId);
}
