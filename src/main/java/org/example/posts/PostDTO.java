package org.example.posts;

class PostDTO {

    private final Integer id;
    private final String url;

    PostDTO(Post post) {
        this.id = post.getId();
        this.url = post.getUrl();
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
