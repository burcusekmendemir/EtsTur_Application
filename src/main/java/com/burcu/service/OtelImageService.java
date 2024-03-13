package com.burcu.service;

import com.burcu.domain.Otel_Image;
import com.burcu.domain.Otel_Room;
import com.burcu.repository.OtelImageRepository;
import com.burcu.repository.OtelRoomRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OtelImageService extends ServiceManager<Otel_Image,String> {

    private final OtelImageRepository repository;

    public OtelImageService(OtelImageRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
