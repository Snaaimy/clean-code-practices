package com.erfanlibrary.api.service;

import com.erfanlibrary.api.exception.book.BookNotAvailableException;
import com.erfanlibrary.api.exception.book.BookNotFoundException;
import com.erfanlibrary.api.exception.user.UserNotFoundException;
import com.erfanlibrary.api.interfaces.AppLogger;
import com.erfanlibrary.api.interfaces.Validator;
import com.erfanlibrary.api.interfaces.book.BorrowStrategy;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.model.UserBook;
import com.erfanlibrary.api.repository.BookRepository;
import com.erfanlibrary.api.repository.UserBookRepository;
import com.erfanlibrary.api.repository.UserRepository;
import com.erfanlibrary.api.service.strategy.PremiumBorrowStrategy;
import com.erfanlibrary.api.service.strategy.StandardBorrowStrategy;
import com.erfanlibrary.api.util.Log4jLogger;
import com.erfanlibrary.api.util.message.book.BookExceptionMessage;
import com.erfanlibrary.api.util.message.user.UserExceptionMessage;
import com.erfanlibrary.api.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LibraryManagementService {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private UserBookRepository userBookRepository;
    private Validator bookValidator;

    private final AppLogger logger = new Log4jLogger(BookService.class);

    @Autowired
    public LibraryManagementService(BookRepository bookRepository,
                                    UserRepository userRepository,
                                    UserBookRepository userBookRepository,
                                    BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userBookRepository = userBookRepository;
        this.bookValidator = bookValidator;

    }

    //    Chapter 6 number 3: Mixing Responsibilities in the Service Layer
    public void borrowBookIncorrectWay(Integer customerId, Integer bookId) {
        // Fetch user and book entities
        User user = userRepository.findById(customerId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        // Perform business logic directly (e.g., directly updating counts)
        if (book.getAvailableCount() <= 0) {
            throw new BookNotAvailableException(BookExceptionMessage.BOOK_NOT_AVAILABLE);
        }
        book.setAvailableCount(book.getAvailableCount() - 1);

        // Create a new UserBook to represent the borrowing
        UserBook cb = new UserBook();
        cb.setUser(user);
        cb.setBook(book);

        // Save the entity and log the event
        userBookRepository.save(cb);
        logger.info("User {} borrowed book {} "+ customerId +", " + bookId);
    }



//   Chapter 6 number 3: not Mixing Responsibilities in the Service Layer
//    Chapter 7 number 1: proper error handling
    public void borrowBook(Integer customerId, Integer bookId) {
        User user = userRepository.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessage.USER_NOT_FOUND));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(BookExceptionMessage.BOOK_NOT_FOUND));
        bookValidator.validate(book);
        processBorrowing(user, book);
    }

    private void processBorrowing(User user, Book book) {
        book.setAvailableCount(book.getAvailableCount() - 1);
        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        userBookRepository.save(userBook);
        logger.info("Applying borrow strategy ");
        applyBorrowStrategy(book, user);
    }

    private void applyBorrowStrategy(Book book, User user){
        BorrowStrategy borrowStrategy;

        if(user.getEmail() == "a@gamil.com"){
            borrowStrategy = new PremiumBorrowStrategy();
        }else {
            borrowStrategy = new StandardBorrowStrategy();
        }
        borrowStrategy.borrow(book, user);
    }

}
