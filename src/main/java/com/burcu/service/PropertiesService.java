package com.burcu.service;


import com.burcu.domain.Properties;
import com.burcu.dto.request.PropertiesSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.PropertiesRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertiesService extends ServiceManager<Properties, String> {

    private final PropertiesRepository propertiesRepository;

    public PropertiesService(PropertiesRepository propertiesRepository) {
        super(propertiesRepository);
        this.propertiesRepository = propertiesRepository;
    }

    public Properties save(PropertiesSaveRequestDto dto) {
        Optional<Properties> propertiesOptional = propertiesRepository.findByName(dto.getName());
        if (propertiesOptional.isPresent()) {
            throw new OtelException(ErrorType.PROPERTIES_ALREADY_EXISTS);
        }
        Properties properties = Properties.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .parentId(dto.getParentId())
                .build();
        return save(properties);
    }
}
