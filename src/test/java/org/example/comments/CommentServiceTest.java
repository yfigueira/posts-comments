package org.example.comments;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Test
    void update_whenUpdatedCommentContentIsNotEmpty_shouldReturnCommentAfterUpdate() {
        // given
        Comment commentBeforeUpdate = new Comment(1, "content before update", null, 1);
        Comment commentAfterUpdate = new Comment(1, "content after update", null, 1);
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.update(commentBeforeUpdate)).thenReturn(Optional.of(commentAfterUpdate));
        CommentService SUT = new CommentService(mockRepository);
        // when
        Comment result = SUT.update(commentBeforeUpdate);
        // then
        assertThat(result, is(commentAfterUpdate));
        assertThat(result, is(commentBeforeUpdate));
        assertThat(commentAfterUpdate.getContent(), is(not(commentBeforeUpdate.getContent())));
    }

    @Test
    void update_whenUpdatedCommentContentIsEmpty_shouldThrowUnauthorizedCommentException() {
        // given
        Comment commentForUpdate = new Comment(1, null, null, 1);
        CommentService SUT = new CommentService(null);
        // when + then
        assertThrows(UnauthorizedCommentException.class, () -> {
            SUT.update(commentForUpdate);
        });
    }

    @Test
    void update_whenCommentToUpdateDoesNotExist_shouldReturnNull() {
        // given
        Comment commentToUpdate = new Comment(-1, "content", null, 1);
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.update(commentToUpdate)).thenReturn(Optional.empty());
        CommentService SUT = new CommentService(mockRepository);
        // when
        Comment result = SUT.update(commentToUpdate);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    void delete_whenCommentToDeleteExists_shouldReturnDeletedCommentId() {
        // given
        Comment commentToDelete = new Comment(1, null,  null, 1);
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.delete(commentToDelete)).thenReturn(Optional.of(1));
        CommentService SUT = new CommentService(mockRepository);
        // when
        Integer result = SUT.delete(commentToDelete);
        // then
        assertThat(result, is(1));
    }

    @Test
    void delete_whenCommentToDeleteDoesNotExist_shouldReturnNull() {
        // given
        Comment commentToDelete = new Comment(-1, null, null, 1);
        CommentRepository mockRepository = mock(CommentRepository.class);
        when(mockRepository.delete(commentToDelete)).thenReturn(Optional.empty());
        CommentService SUT = new CommentService(mockRepository);
        // when
        Integer result = SUT.delete(commentToDelete);
        // then
        assertThat(result, is(nullValue()));
    }
}
