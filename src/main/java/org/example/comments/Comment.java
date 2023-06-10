package org.example.comments;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "post_id")
    private Integer postId;

    Comment() { }

    public Comment(Integer id, String content, LocalDate date, Integer postId) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.postId = postId;
    }

    public Comment(String content, LocalDate date, Integer postId) {
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

    public void setDate(LocalDate date) {
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
