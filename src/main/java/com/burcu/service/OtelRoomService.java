package com.burcu.service;

import com.burcu.domain.Otel_Room;
import com.burcu.repository.OtelRoomRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OtelRoomService extends ServiceManager<Otel_Room,String> {

    private final OtelRoomRepository repository;

    public OtelRoomService(OtelRoomRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
