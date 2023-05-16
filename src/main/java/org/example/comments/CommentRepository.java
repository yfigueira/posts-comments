package org.example.comments;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {


    List<Comment> findByPostId(Integer postId);

    Comment add(Comment newComment);

    Optional<Comment> update(Comment comment);
}
