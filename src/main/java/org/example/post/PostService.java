package org.example.post;

import java.util.List;

class PostService {

    private final PostRepository repository;

    PostService(PostRepository repository) {
        this.repository = repository;
    }

    List<Post> findAll() {
        return repository.findAll();
    }

    Post findById(Integer id) {
        return repository.findById(id).get();
    }
}
