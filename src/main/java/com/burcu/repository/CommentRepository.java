package com.burcu.repository;

import com.burcu.domain.Category;
import com.burcu.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
