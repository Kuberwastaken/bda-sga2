package com.bda.sga2.service;

import com.bda.sga2.entity.Author;
import com.bda.sga2.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void getAllAuthors() {
        List<Author> authors = Arrays.asList(
            new Author("Peter Thiel", "American", 1967),
            new Author("Eric Ries", "American", 1978)
        );
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();
        assertThat(result).hasSize(2);
        verify(authorRepository).findAll();
    }

    @Test
    void getAuthorById_found() {
        Author author = new Author("John Green", "American", 1977);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("John Green");
    }

    @Test
    void getAuthorById_notFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThat(authorService.getAuthorById(99L)).isEmpty();
    }

    @Test
    void saveAuthor() {
        Author author = new Author("Malcolm Gladwell", "Canadian", 1963);
        when(authorRepository.save(author)).thenReturn(author);

        Author saved = authorService.saveAuthor(author);
        assertThat(saved.getName()).isEqualTo("Malcolm Gladwell");
        verify(authorRepository).save(author);
    }
}
