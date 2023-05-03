package org.example.post;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
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
        List<Post> result = SUT.findAll();
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
        List<Post> result = SUT.findAll();
        // then
        assertThat(result, is(empty()));
    }
}