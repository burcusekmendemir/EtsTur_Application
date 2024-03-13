package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByOtelId(String id);
}
