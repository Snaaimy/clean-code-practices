package com.erfanlibrary.api.validator;

import com.erfanlibrary.api.exception.book.BookNotAvailableException;
import com.erfanlibrary.api.interfaces.Validator;
import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.util.message.book.BookExceptionMessage;
import org.springframework.stereotype.Component;

@Component
public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book book) {
        this.validateAvailability(book);
    }

    private void validateAvailability(Book book) {
        if (book.getAvailableCount() <= 0) {
            throw new BookNotAvailableException(BookExceptionMessage.BOOK_NOT_AVAILABLE);
        }
    }


}
