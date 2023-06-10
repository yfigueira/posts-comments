package org.example.comments;

import java.util.Optional;

public class CommentH2DBRepository  implements  CommentRepository{

    @Override
    public Comment add(Comment newComment) {
        return null;
    }

    @Override
    public Optional<Comment> update(Comment comment) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> delete(Comment comment) {
        return Optional.empty();
    }

    @Override
    public Integer countByPostId(Integer postId) {
        return null;
    }
}
