package com.burcu.service;

import com.burcu.domain.Otel_Image;
import com.burcu.domain.Room_Image;
import com.burcu.repository.OtelImageRepository;
import com.burcu.repository.RoomImageRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class RoomImageService extends ServiceManager<Room_Image,String> {

    private final RoomImageRepository repository;

    public RoomImageService(RoomImageRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
