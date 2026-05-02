package com.bda.sga2.controller;

import com.bda.sga2.entity.Author;
import com.bda.sga2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/add";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author, Model model) {
        try {
            authorService.saveAuthor(author);
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Could not save author: data integrity violation.");
            model.addAttribute("author", author);
            return "authors/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isEmpty()) {
            return "redirect:/authors";
        }
        model.addAttribute("author", author.get());
        return "authors/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute Author author, Model model) {
        try {
            author.setId(id);
            authorService.saveAuthor(author);
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Could not update author: data integrity violation.");
            model.addAttribute("author", author);
            return "authors/edit";
        }
    }
}
