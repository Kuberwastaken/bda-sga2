package com.bda.sga2.repository;

import com.bda.sga2.entity.Author;
import com.bda.sga2.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        savedAuthor = authorRepository.save(new Author("Eric Ries", "American", 1978));
    }

    @Test
    void saveAndFindById() {
        Book book = new Book("The Lean Startup", "Business", 2011, "978-0307887894", savedAuthor);
        Book saved = bookRepository.save(book);

        assertThat(bookRepository.findById(saved.getId())).isPresent();
        assertThat(saved.getTitle()).isEqualTo("The Lean Startup");
    }

    @Test
    void findAllBooksWithAuthors_returnsOnlyBooksWithAuthors() {
        bookRepository.save(new Book("The Lean Startup", "Business", 2011, "978-0307887894", savedAuthor));

        Author anotherAuthor = authorRepository.save(new Author("Peter Thiel", "American", 1967));
        bookRepository.save(new Book("Zero to One", "Business", 2014, "978-0804139021", anotherAuthor));

        List<Book> books = bookRepository.findAllBooksWithAuthors();
        assertThat(books).hasSize(2);
        assertThat(books).allMatch(b -> b.getAuthor() != null);
    }

    @Test
    void findAllBooksWithAuthors_excludesBooksWithoutAuthor() {
        bookRepository.save(new Book("Orphan Book", "Test", 2020, "000-0000000001", null));

        List<Book> books = bookRepository.findAllBooksWithAuthors();
        assertThat(books).isEmpty();
    }

    @Test
    void updateBook() {
        Book saved = bookRepository.save(new Book("The Lean Startup", "Business", 2011, "978-0307887894", savedAuthor));
        saved.setGenre("Entrepreneurship");
        bookRepository.save(saved);

        Book updated = bookRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getGenre()).isEqualTo("Entrepreneurship");
    }
}
