package org.example.comments;

import java.util.List;

public interface CommentRepository {


    List<Comment> findByPostId(Integer postId);

    Comment add(Comment newComment);
}
