package com.bda.sga2.repository;

import com.bda.sga2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b INNER JOIN b.author a")
    List<Book> findAllBooksWithAuthors();
}
