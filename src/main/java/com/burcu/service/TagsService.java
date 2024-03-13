package com.burcu.service;


import com.burcu.domain.Otel;
import com.burcu.domain.Tags;
import com.burcu.dto.request.TagsSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.TagsRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsService extends ServiceManager<Tags, String> {

    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        super(tagsRepository);
        this.tagsRepository = tagsRepository;
    }

    public List<Tags> findByIdContaining(List<String> tagsList) {
        return tagsRepository.findByIdContaining(tagsList);
    }

    public Tags save(TagsSaveRequestDto dto) {
        Optional<Tags> tagsOptional = tagsRepository.findByName(dto.getName());
        if (tagsOptional.isPresent()) {
            throw new OtelException(ErrorType.TAGS_ALREADY_EXISTS);
        }

        return save(Tags.builder().name(dto.getName()).build());
    }
}
