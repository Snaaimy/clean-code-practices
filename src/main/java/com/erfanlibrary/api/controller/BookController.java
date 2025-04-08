package com.erfanlibrary.api.controller;

import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/com/erfanlibrary/api/v1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        ResponseEntity<Book> response = new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookList() {
        return new ResponseEntity<>(bookService.getAllBookList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getListOfBooks() {
        return new ResponseEntity<>(bookService.getListOfBooks(), HttpStatus.OK);
    }


}
