package com.bda.sga2.controller;

import com.bda.sga2.entity.Author;
import com.bda.sga2.entity.Book;
import com.bda.sga2.service.AuthorService;
import com.bda.sga2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooksWithAuthors());
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam Long authorId, Model model) {
        try {
            Optional<Author> author = authorService.getAuthorById(authorId);
            author.ifPresent(book::setAuthor);
            bookService.saveBook(book);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Could not save book: a book with that ISBN already exists.");
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isEmpty()) {
            return "redirect:/books";
        }
        model.addAttribute("book", book.get());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book,
                             @RequestParam Long authorId, Model model) {
        try {
            book.setId(id);
            Optional<Author> author = authorService.getAuthorById(authorId);
            author.ifPresent(book::setAuthor);
            bookService.saveBook(book);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Could not update book: a book with that ISBN already exists.");
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/edit";
        }
    }
}
