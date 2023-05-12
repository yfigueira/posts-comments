package org.example.comments;

import java.time.LocalDate;

public class Comment {

    private Integer id;

    private String content;

    private LocalDate date;

    private Integer postId;

    Comment() { }

    Comment(Integer id, String content, LocalDate date, Integer postId) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPostId() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
