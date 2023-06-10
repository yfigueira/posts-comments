package org.example.comments;

import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> update(Comment comment);

    Optional<Integer> delete(Comment comment);
}
