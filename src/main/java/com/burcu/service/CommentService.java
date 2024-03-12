package com.burcu.service;


import com.burcu.domain.Category;
import com.burcu.domain.Comment;
import com.burcu.repository.CategoryRepository;
import com.burcu.repository.CommentRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceManager<Comment, String> {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }
}
