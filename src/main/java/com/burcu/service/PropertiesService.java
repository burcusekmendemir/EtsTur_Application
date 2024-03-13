package com.burcu.service;


import com.burcu.domain.Properties;
import com.burcu.dto.request.PropertiesSaveRequestDto;
import com.burcu.dto.response.FilterListResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.PropertiesRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * otel/filter-list (sol tarafta bulunan filtreleme başlıklarını listelemek için kullanılacak)
     * @return
     */

    public Map<Properties, List<Properties>> filterList() {
        List<Properties> allProperties = propertiesRepository.findAll();
        Map<Properties, List<Properties>> propertiesWithSubProperties = new HashMap<>();
        for (Properties property : allProperties) {
            List<Properties> subProperties = propertiesRepository.findByParentId(property.getId());
            if (Objects.isNull(property.getParentId()) || property.getParentId().isEmpty())
                 propertiesWithSubProperties.put(property, subProperties);
        }
        return propertiesWithSubProperties;
    }

    /**
     * otel/find-all (popüler otellerden başlayarak ana sayfada gösterilecek otellerin listesini dönecek -)
     */




}


