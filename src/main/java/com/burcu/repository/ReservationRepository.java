package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
