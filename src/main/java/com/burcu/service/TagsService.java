package com.burcu.service;


import com.burcu.domain.Tags;
import com.burcu.repository.TagsRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TagsService extends ServiceManager<Tags, String> {

    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        super(tagsRepository);
        this.tagsRepository = tagsRepository;
    }
}
