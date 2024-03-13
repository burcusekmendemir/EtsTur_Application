package com.burcu.repository;


import com.burcu.domain.Otel_Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtelCommentRepository extends MongoRepository<Otel_Comment, String> {


}
