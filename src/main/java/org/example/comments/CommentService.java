package org.example.comments;

import java.util.List;

public class CommentService {

    private final CommentRepository repository;

    CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    CommentService() {
        this(new CommentH2DBRepository());
    }

    Comment update(Comment comment) {

        if (comment.getContent() == null) throw new UnauthorizedCommentException("Empty Comment");

        return repository.update(comment).orElse(null);
    }

    Integer delete(Comment comment) {
        return repository.delete(comment).orElse(null);
    }
}
