package com.burcu.service;


import com.burcu.domain.Reservation;
import com.burcu.repository.ReservationRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends ServiceManager<Reservation, String> {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }
}
