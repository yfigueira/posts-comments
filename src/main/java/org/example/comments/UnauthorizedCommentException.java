package org.example.comments;

public class UnauthorizedCommentException extends RuntimeException {

    UnauthorizedCommentException() {
    }

    public UnauthorizedCommentException(String message) {
        super(message);
    }
}
