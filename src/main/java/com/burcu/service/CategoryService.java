package com.burcu.service;


import com.burcu.domain.Category;
import com.burcu.dto.request.CategorySaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.CategoryRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends ServiceManager<Category, String> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public Category save(CategorySaveRequestDto dto) {

        Optional<Category> categoryOptional=categoryRepository.findByName(dto.getName());
        if (categoryOptional.isPresent()){
            throw new OtelException(ErrorType.CATEGORY_ALREADY_EXISTS);
        }

        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        return save(category);
    }
}
