package com.bda.sga2.service;

import com.bda.sga2.entity.Author;
import com.bda.sga2.entity.Book;
import com.bda.sga2.repository.BookRepository;
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
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooksWithAuthors() {
        Author author = new Author("Eric Ries", "American", 1978);
        List<Book> books = Arrays.asList(
            new Book("The Lean Startup", "Business", 2011, "978-0307887894", author),
            new Book("Zero to One", "Business", 2014, "978-0804139021", new Author("Peter Thiel", "American", 1967))
        );
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(books);

        List<Book> result = bookService.getAllBooksWithAuthors();
        assertThat(result).hasSize(2);
        verify(bookRepository).findAllBooksWithAuthors();
    }

    @Test
    void getBookById_found() {
        Book book = new Book("The Fault in Our Stars", "Fiction", 2012, "978-0525478812", null);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("The Fault in Our Stars");
    }

    @Test
    void getBookById_notFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThat(bookService.getBookById(99L)).isEmpty();
    }

    @Test
    void saveBook() {
        Author author = new Author("John Green", "American", 1977);
        Book book = new Book("The Fault in Our Stars", "Fiction", 2012, "978-0525478812", author);
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.saveBook(book);
        assertThat(saved.getIsbn()).isEqualTo("978-0525478812");
        verify(bookRepository).save(book);
    }
}
