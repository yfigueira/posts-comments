package org.example.posts;

import java.util.List;

class PostService {

    private static final Post FALLBACK_POST = new Post(
            0,
            "https://cdn.pixabay.com/photo/2016/02/11/22/01/mistake-1194670_960_720.png",
            null,
            null,
            "Post Not Found"
    );

    private final PostRepository repository;

    PostService(PostRepository repository) {
        this.repository = repository;
    }

    List<Post> findAll() {
        return repository.findAll();
    }

    Post findById(Integer id) {
        return repository.findById(id).orElse(FALLBACK_POST);
    }

    static Post fallbackPost() {
        return FALLBACK_POST;
    }
}
