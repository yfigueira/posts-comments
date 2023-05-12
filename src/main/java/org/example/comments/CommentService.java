package org.example.comments;

import java.util.List;

public class CommentService {

    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    List<Comment> findByPostId(Integer postId) {
        return repository.findByPostId(postId);
    }
}
