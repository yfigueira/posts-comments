package org.example.posts;

class PostDTO {

    private final Integer id;
    private final String url;

    PostDTO(Post post) {
        this.id = post.getId();
        this.url = post.getUrl();
    }

    Integer getId() {
        return id;
    }

    String getUrl() {
        return url;
    }
}
