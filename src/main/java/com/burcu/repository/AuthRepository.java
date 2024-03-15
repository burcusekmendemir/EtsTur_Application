package com.burcu.repository;

import com.burcu.domain.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);

    Optional<Auth> findByUsernameAndPassword(String username, String password);

    Optional<Auth> findByUsername(String username);

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);

    Optional<Auth> findByActivationCode(String activationCode);
}
