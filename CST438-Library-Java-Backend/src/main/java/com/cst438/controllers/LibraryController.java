package com.cst438.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.domain.Book;
import com.cst438.domain.BookDTO;
import com.cst438.domain.BookRepository;
import com.cst438.domain.Patron;
import com.cst438.domain.PatronDTO;
import com.cst438.domain.PatronRepository;

@RestController
@Transactional
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @PutMapping("/book/{bookId}/checkout/{patronId}")
    public ResponseEntity<String> checkoutBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        // Check if book and patron exist in the database
        Book book = bookRepository.findById(bookId).orElse(null);
        Patron patron = patronRepository.findById(patronId).orElse(null);

        if (book == null || patron == null) {
            return ResponseEntity.notFound().build();
        }

        // Perform the checkout operation (Update the checkoutDate in the book entity)
        book.setCheckoutDate("2023-07-28");
        book.setCheckoutPatron(patron); // Set the checkoutPatron property in the Book entity

        bookRepository.save(book); // Save the book (updates the checkoutDate and the checkoutPatron)

        return ResponseEntity.ok("Book successfully checked out");
    }

    @PutMapping("/book/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        // Check if book exists in the database
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the associated Patron entity
        Patron patron = book.getCheckoutPatron();

        // Perform the return operation (Set the checkoutDate to null in the book entity)
        book.setCheckoutDate(null);
        book.setCheckoutPatron(null); // Remove the association with the patron
        bookRepository.save(book); // Save the book (updates the checkoutDate and checkoutPatron)

        // Update the Patron entity's list of books by removing the returned book
        if (patron != null) {
            List<Book> books = patron.getBooks();
            books.remove(book);
            patron.setBooks(books);
            patronRepository.save(patron); // Save the patron (updates the list of books)
        }

        return ResponseEntity.ok("Book successfully returned");
    }

    @GetMapping("/patron/{patronId}")
    public ResponseEntity<PatronDTO> getPatron(@PathVariable Long patronId) {
        Patron patron = patronRepository.findById(patronId).orElse(null);

        if (patron == null) {
            return ResponseEntity.notFound().build();
        }

        PatronDTO patronDTO = new PatronDTO();
        patronDTO.setPatronId(patron.getPatronId());
        patronDTO.setName(patron.getName());

        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book : patron.getBooks()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookId(book.getBookId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setCheckoutDate(book.getCheckoutDate());
            booksDTO.add(bookDTO);
        }

        patronDTO.setBooks(booksDTO);

        return ResponseEntity.ok(patronDTO);
    }
}