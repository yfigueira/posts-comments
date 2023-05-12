package org.example.comments;

public class UnauthorizedCommentException extends RuntimeException {

    UnauthorizedCommentException() {
    }

    UnauthorizedCommentException(String message) {
        super(message);
    }
}
