package com.burcu.service;

import com.burcu.domain.Otel_Comment;
import com.burcu.domain.Otel_Image;
import com.burcu.repository.OtelCommentRepository;
import com.burcu.repository.OtelImageRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OtelCommentService extends ServiceManager<Otel_Comment,String> {

    private final OtelCommentRepository repository;

    public OtelCommentService(OtelCommentRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
