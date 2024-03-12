package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
