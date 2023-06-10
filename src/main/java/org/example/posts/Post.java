package org.example.posts;

import jakarta.persistence.*;
import org.example.comments.Comment;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "number_of_likes")
    private Integer numberOfLikes;

    @Column(name = "description")
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    Post() { }

    Post(Integer id, String url, LocalDate date, Integer numberOfLikes, String description) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.numberOfLikes = numberOfLikes;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
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

    void addComment(Comment comment) {
        comments.add(comment);
    }
}
