package org.example.comments;

import java.util.List;

public class CommentService {

    private final CommentRepository repository;

    CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    List<Comment> findByPostId(Integer postId) {
        return repository.findByPostId(postId);
    }

    Comment add(Comment newComment) throws UnauthorizedCommentException {

        if (newComment.getContent() == null) throw new UnauthorizedCommentException("Empty Comment");

        return repository.add(newComment);
    }
}
