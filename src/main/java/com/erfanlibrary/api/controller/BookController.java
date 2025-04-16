package com.erfanlibrary.api.controller;

import com.erfanlibrary.api.dto.book.BookDto;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/com/erfanlibrary/api/v1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
        BookDto savedBook = bookService.addBook(book);
        ResponseEntity<BookDto> response = new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBookList() {
        return new ResponseEntity<>(bookService.getBookList(), HttpStatus.OK);
    }


    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getListOfBooks() {
        return new ResponseEntity<>(bookService.getAvailableBookList(), HttpStatus.OK);
    }


}
