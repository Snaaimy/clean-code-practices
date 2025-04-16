package com.erfanlibrary.api.mapper;

import com.erfanlibrary.api.dto.book.BookDto;
import com.erfanlibrary.api.model.Book;

public class BookDtoToBook {
    public static Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPageCount(bookDto.getPageCount());
        book.setAvailableCount(bookDto.getAvailableCount());
        return book;
    }
}
