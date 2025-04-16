package com.erfanlibrary.api.service;

import com.erfanlibrary.api.dto.book.BookDto;
import com.erfanlibrary.api.exception.book.BookNotFoundException;
import com.erfanlibrary.api.mapper.BookDtoToBook;
import com.erfanlibrary.api.mapper.BookToBookDto;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.repository.BookRepository;
import com.erfanlibrary.api.interfaces.AppLogger;
import com.erfanlibrary.api.util.Log4jLogger;
import com.erfanlibrary.api.util.message.book.BookExceptionMessage;
import com.erfanlibrary.api.util.message.book.BookMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AppLogger logger = new Log4jLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private boolean isBookAvailable(Book book) {
        return book.getAvailableCount() > 0;
    }

    public Book checkBookExist(Integer id) {
        Book book = bookRepository.findOneById(id);
        if (book == null) {
            throw new BookNotFoundException(BookExceptionMessage.BOOK_NOT_FOUND);
        }
        return book;
    }

    public BookDto addBook(BookDto bookDto){
        Book book = BookDtoToBook.toBook(bookDto);
        return BookToBookDto.toBookDto(bookRepository.save(book));
    }

    public List<BookDto> getBookList() {
        return BookToBookDto.toBookDto(bookRepository.findAll());
    }

    public BookDto getBookById(Integer id) {

        return BookToBookDto.toBookDto(checkBookExist(id));
    }

    public List<BookDto> getAvailableBookList() {
        List<Book> bookList = bookRepository.findAll();
        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (isBookAvailable(book)) {
                availableBookList.add(book);
            }
        }
        return BookToBookDto.toBookDto(availableBookList);
    }

    public BookDto updateBookById(Integer id, Book book) {
        Book foundedBook = this.checkBookExist(id);

        foundedBook.setTitle(book.getTitle());
        foundedBook.setAuthor(book.getAuthor());
        foundedBook.setAvailableCount(book.getAvailableCount());
        foundedBook.setPageCount(book.getPageCount());

        logger.info(BookMessage.BOOK_UPDATED_SUCCESSFULLY +": " + foundedBook.getTitle());

        return BookToBookDto.toBookDto(bookRepository.save(foundedBook));

    }

}
