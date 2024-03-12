package com.burcu.service;

import com.burcu.domain.Payment;
import com.burcu.repository.PaymentRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends ServiceManager<Payment, String> {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
        this.paymentRepository = paymentRepository;
    }
}
