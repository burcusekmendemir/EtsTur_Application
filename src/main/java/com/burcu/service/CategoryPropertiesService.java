package com.burcu.service;


import com.burcu.domain.Category_Properties;
import com.burcu.dto.request.CategoryPropertiesSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.CategoryPropertiesRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryPropertiesService extends ServiceManager<Category_Properties, String> {

    private final CategoryPropertiesRepository categoryPropertiesRepository;

    public CategoryPropertiesService(CategoryPropertiesRepository categoryPropertiesRepository) {
        super(categoryPropertiesRepository);
        this.categoryPropertiesRepository = categoryPropertiesRepository;
    }

    public Category_Properties save(CategoryPropertiesSaveRequestDto dto) {
        Optional<Category_Properties> categoryPropertiesOptional = categoryPropertiesRepository.findByCategoryIdAndPropertiesId(dto.getCategoryId(), dto.getPropertiesId());
        if (categoryPropertiesOptional.isPresent()) {
            throw new OtelException(ErrorType.CATEGORY_PROPERTIES_ALREADY_EXISTS);
        }

        Category_Properties categoryProperties = Category_Properties.builder()
                .categoryId(dto.getCategoryId())
                .propertiesId(dto.getPropertiesId())
                .build();

        return save(categoryProperties);
    }
}
