package org.example.comments;

import java.util.Optional;

public class CommentH2DBRepository  implements  CommentRepository{

    @Override
    public Optional<Comment> update(Comment comment) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> delete(Comment comment) {
        return Optional.empty();
    }
}
