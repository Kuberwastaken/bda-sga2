package com.bda.sga2.repository;

import com.bda.sga2.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void saveAndFindById() {
        Author author = new Author("Peter Thiel", "American", 1967);
        Author saved = authorRepository.save(author);

        Optional<Author> found = authorRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Peter Thiel");
        assertThat(found.get().getNationality()).isEqualTo("American");
    }

    @Test
    void findAll() {
        authorRepository.save(new Author("Eric Ries", "American", 1978));
        authorRepository.save(new Author("John Green", "American", 1977));

        List<Author> authors = authorRepository.findAll();
        assertThat(authors).hasSize(2);
    }

    @Test
    void updateAuthor() {
        Author saved = authorRepository.save(new Author("Malcolm Gladwell", "Canadian", 1963));
        saved.setNationality("Canadian-British");
        authorRepository.save(saved);

        Author updated = authorRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getNationality()).isEqualTo("Canadian-British");
    }

    @Test
    void deleteById() {
        Author saved = authorRepository.save(new Author("Seth Godin", "American", 1960));
        authorRepository.deleteById(saved.getId());

        assertThat(authorRepository.findById(saved.getId())).isEmpty();
    }
}
