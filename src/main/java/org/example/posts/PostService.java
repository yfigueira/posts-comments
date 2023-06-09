package org.example.posts;

import org.example.comments.Comment;
import org.example.comments.UnauthorizedCommentException;

import java.util.List;

import static java.util.stream.Collectors.*;

class PostService {

    private static final Post FALLBACK_POST = new Post(
            0,
            "https://cdn.pixabay.com/photo/2016/02/11/22/01/mistake-1194670_960_720.png",
            null,
            null,
            "Post Not Found"
    );

    private final PostRepository repository;

    PostService() {
        this(new PostH2DBRepository());
    }

    PostService(PostRepository repository) {
        this.repository = repository;
    }

    List<PostDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PostDTO::new)
                .collect(toList());
    }

    Post findById(Integer id) {
        return repository.findById(id).orElse(FALLBACK_POST);
    }

    Post update(Post post) {
        return repository.update(post).orElse(FALLBACK_POST);
    }

    static Post fallbackPost() {
        return FALLBACK_POST;
    }

    List<Comment> addComment(Comment comment) {
        if (comment.getContent() == null) throw new UnauthorizedCommentException("Empty Content");
        return repository.addComment(comment);
    }
}
