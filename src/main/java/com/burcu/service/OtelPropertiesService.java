package com.burcu.service;


import com.burcu.domain.Otel_Properties;
import com.burcu.repository.OtelPropertiesRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OtelPropertiesService extends ServiceManager<Otel_Properties,String> {

    private final OtelPropertiesRepository repository;

    public OtelPropertiesService(OtelPropertiesRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
