package org.example.comments;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {


    List<Comment> findByPostId(Integer postId);

    Comment add(Comment newComment);

    Optional<Comment> update(Comment comment);

    Optional<Integer> delete(Comment comment);

    Integer countByPostId(Integer postId);
}
