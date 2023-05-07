package org.example.posts;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @Test
    void findALl_whenRepositoryHasPosts_shouldReturnAllPosts() {
        // given
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findAll()).thenReturn(List.of(
            new Post(1, null, null, null, null),
            new Post(2, null, null, null, null),
            new Post(3, null, null, null, null)
        ));
        PostService SUT = new PostService(mockRepository);
        // when
        List<PostDTO> result = SUT.findAll();
        // then
        assertThat(result.size(), is(3));
    }

    @Test
    void findAll_whenRepositoryIsEmpty_ShouldReturnEmptyListOfPosts() {
        // given
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findAll()).thenReturn(List.of());
        PostService SUT = new PostService(mockRepository);
        // when
        List<PostDTO> result = SUT.findAll();
        // then
        assertThat(result, is(empty()));
    }

    @Test
    void findById_whenRequestedPostExists_shouldReturnRequestedPost() {
        // given
        Post requestedPost = new Post(10, null, null, null, null);
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(10)).thenReturn(Optional.of(requestedPost));
        PostService SUT = new PostService(mockRepository);
        // when
        Post result = SUT.findById(10);
        // then
        assertThat(result, is(requestedPost));
    }

    @Test
    void findById_whenRequestedPostDoesNotExist_shouldReturnFallbackPost() {
        // given
        Post requestedPost = new Post(-1, null, null, null, null);
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(-1)).thenReturn(Optional.of(PostService.fallbackPost()));
        PostService SUT = new PostService(mockRepository);
        // when
        Post result = SUT.findById(-1);
        // then
        assertThat(result, is(PostService.fallbackPost()));
    }

    @Test
    void update_whenPostToUpdateExists_shouldReturnPostWithUpdatedDescription() {
        // given
        Post postBeforeUpdate = new Post(10, null, null, null, "Description before update");
        Post postAfterUpdate = new Post(10, null, null, null, "Description after update");
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.update(postBeforeUpdate)).thenReturn(Optional.of(postAfterUpdate));
        PostService SUT = new PostService(mockRepository);
        // when
        Post result = SUT.update(postBeforeUpdate);
        // then
        assertThat(result, is(postAfterUpdate));
        assertThat(postBeforeUpdate, is(postAfterUpdate));
        assertThat(postBeforeUpdate.getDescription(), is(not(postAfterUpdate.getDescription())));
    }

    @Test
    void update_whenPostToUpdateDoesNotExist_shouldReturnFallbackPost() {
        // given
        Post notExistingPost = new Post(-1, null, null, null, null);
        PostRepository mockRepository = mock(PostRepository.class);
        when(mockRepository.update(notExistingPost)).thenReturn(Optional.of(PostService.fallbackPost()));
        PostService SUT = new PostService(mockRepository);
        // when
        Post result = SUT.update(notExistingPost);
        // then
        assertThat(result, is(PostService.fallbackPost()));
    }
}
