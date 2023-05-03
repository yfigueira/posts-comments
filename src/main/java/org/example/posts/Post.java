package org.example.posts;

import java.time.LocalDate;

class Post {

    private Integer id;
    private String url;
    private LocalDate date;
    private Integer numberOfLikes;
    private String description;

    Post(Integer id, String url, LocalDate date, Integer numberOfLikes, String description) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.numberOfLikes = numberOfLikes;
        this.description = description;
    }

    Integer getId() {
        return id;
    }

    String getUrl() {
        return url;
    }

    LocalDate getDate() {
        return date;
    }

    Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
