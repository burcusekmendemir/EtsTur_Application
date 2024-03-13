package com.burcu.repository;

import com.burcu.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, String> {

    Optional<Address> findByDistirctIdAndStreetNumber(String distirctId, String streetNumber);

    List<Address> findByNameContainingIgnoreCase(String name);

    List<Address> findByNameContainingIgnoreCaseOrStreetNumberContainingIgnoreCase(String name, String number);

}
