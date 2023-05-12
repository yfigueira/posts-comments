package org.example.comments;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    @Test
    void findByPostId_whenPostHasComments_shouldReturnAllAssociatedComments() {
        // given
        int postId = 1;
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.findByPostId(postId)).thenReturn(List.of(
                new Comment(1, null, null, 1),
                new Comment(2, null, null, 1),
                new Comment(3, null, null, 1)
        ));
        CommentService SUT = new CommentService(mockRepository);
        // when
        List<Comment> result = SUT.findByPostId(postId);
        // then
        assertThat(result, hasSize(3));
    }

    @Test
    void findByPostId_whenPostDoesNotHaveAnyComments_shouldReturnEmptyListOfComments() {
        // given
        int postId = 1;
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.findByPostId(postId)).thenReturn(List.of());
        CommentService SUT = new CommentService(mockRepository);
        // when
        List<Comment> result = SUT.findByPostId(postId);
        // then
        assertThat(result, is(empty()));
    }

    @Test
    void add_whenNewCommentContentIsNotEmpty_shouldReturnAddedComment() {
        // given
        Comment newComment = new Comment(null, "new comment content", null, 1);
        Comment newCommentFromRepo = new Comment(1, "new comment content", null, 1);
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.add(newComment)).thenReturn(newCommentFromRepo);
        CommentService SUT = new CommentService(mockRepository);
        // when
        Comment result = SUT.add(newComment);
        // then
        assertThat(result, is(newCommentFromRepo));
    }

    @Test
    void add_whenNewCommentContentIsEmpty_shouldThrowUnauthorizedCommentException() {
        // given
        Comment newComment = new Comment(null, null, null, 1);
        CommentService SUT = new CommentService(null);
        // when + then
        assertThrows(UnauthorizedCommentException.class, () -> {
            SUT.add(newComment);
        });
    }
}
