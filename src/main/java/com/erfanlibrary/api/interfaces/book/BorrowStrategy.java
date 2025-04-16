package com.erfanlibrary.api.interfaces.book;

import com.erfanlibrary.api.dto.book.BookDto;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.model.User;

public interface BorrowStrategy {

    void borrow(Book book, User user);
}
