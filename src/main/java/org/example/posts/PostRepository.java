package org.example.posts;

import org.example.comments.Comment;

import java.util.List;
import java.util.Optional;

interface PostRepository {

    List<Post> findAll();

    Optional<Post> findById(Integer id);

    Optional<Post> update(Post post);

    List<Comment> addComment(Comment newComment);
}
