package com.erfanlibrary.api.service.strategy;

import com.erfanlibrary.api.interfaces.AppLogger;
import com.erfanlibrary.api.interfaces.book.BorrowStrategy;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.service.BookService;
import com.erfanlibrary.api.util.Log4jLogger;

public class PremiumBorrowStrategy implements BorrowStrategy {

    private final AppLogger logger = new Log4jLogger(BookService.class);

    @Override
    public void borrow(Book book, User user) {

        logger.info("Applying premium borrow strategy");

        logger.info("Borrowing book " + book.getTitle() + " for user " + user.getFullName());
        logger.info("Borrowing period: 30 days");
        logger.info("Borrowing fee: $5.00");

    }
}
