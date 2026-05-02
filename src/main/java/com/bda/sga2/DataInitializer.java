package com.bda.sga2;

import com.bda.sga2.entity.Author;
import com.bda.sga2.entity.Book;
import com.bda.sga2.repository.AuthorRepository;
import com.bda.sga2.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        Author peterThiel         = authorRepository.save(new Author("Peter Thiel",          "American", 1967));
        Author ericRies            = authorRepository.save(new Author("Eric Ries",             "American", 1978));
        Author johnGreen           = authorRepository.save(new Author("John Green",            "American", 1977));
        Author malcolmGladwell     = authorRepository.save(new Author("Malcolm Gladwell",      "Canadian", 1963));
        Author claytonChristensen  = authorRepository.save(new Author("Clayton Christensen",   "American", 1952));
        Author sethGodin           = authorRepository.save(new Author("Seth Godin",            "American", 1960));
        Author simonSinek          = authorRepository.save(new Author("Simon Sinek",           "British",  1973));
        Author sherylSandberg      = authorRepository.save(new Author("Sheryl Sandberg",       "American", 1969));
        Author walterIsaacson      = authorRepository.save(new Author("Walter Isaacson",       "American", 1952));
        Author michaelLewis        = authorRepository.save(new Author("Michael Lewis",         "American", 1960));

        bookRepository.save(new Book("Zero to One",              "Business",    2014, "978-0804139021", peterThiel));
        bookRepository.save(new Book("The Lean Startup",         "Business",    2011, "978-0307887894", ericRies));
        bookRepository.save(new Book("The Fault in Our Stars",   "Fiction",     2012, "978-0525478812", johnGreen));
        bookRepository.save(new Book("The Tipping Point",        "Business",    2000, "978-0316346627", malcolmGladwell));
        bookRepository.save(new Book("The Innovator's Dilemma",  "Business",    1997, "978-1633691780", claytonChristensen));
        bookRepository.save(new Book("Purple Cow",               "Marketing",   2003, "978-1591843177", sethGodin));
        bookRepository.save(new Book("Start With Why",           "Leadership",  2009, "978-1591846444", simonSinek));
        bookRepository.save(new Book("Lean In",                  "Business",    2013, "978-0385349949", sherylSandberg));
        bookRepository.save(new Book("Steve Jobs",               "Biography",   2011, "978-1451648539", walterIsaacson));
        bookRepository.save(new Book("The Big Short",            "Finance",     2010, "978-0393338829", michaelLewis));
    }
}
