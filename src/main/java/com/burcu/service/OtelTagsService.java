package com.burcu.service;


import com.burcu.domain.Otel_Tags;
import com.burcu.repository.OtelTagsRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtelTagsService extends ServiceManager<Otel_Tags, String> {

    private final OtelTagsRepository otelTagsRepository;

    public OtelTagsService(OtelTagsRepository otelTagsRepository) {
        super(otelTagsRepository);
        this.otelTagsRepository = otelTagsRepository;
    }

    public List<Otel_Tags> findByOtelId(String id) {
        return otelTagsRepository.findByOtelId(id);
    }
}
