package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.UserProfile;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByAuthId(String s);
}
