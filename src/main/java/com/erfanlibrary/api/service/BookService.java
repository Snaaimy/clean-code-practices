package com.erfanlibrary.api.service;

import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBookList() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        Book foundedBook = bookRepository.findOneById(id);
        if (foundedBook == null) {
            throw new IllegalStateException("Book not found");
        }
        return foundedBook;
    }

    public List<Book> getListOfBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<Book> availableBookList = bookList.stream().filter(book -> book.getAvailableCount() > 0).collect(Collectors.toList());
        return availableBookList;
    }
}
