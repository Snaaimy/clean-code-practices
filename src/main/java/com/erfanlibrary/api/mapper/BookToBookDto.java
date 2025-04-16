package com.erfanlibrary.api.mapper;

import com.erfanlibrary.api.dto.book.BookDto;
import com.erfanlibrary.api.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookToBookDto {

    public static BookDto toBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAvailableCount(book.getAvailableCount());
        bookDto.setPageCount(book.getPageCount());

        return bookDto;
    }

    public static List<BookDto> toBookDto(List<Book> bookList){
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : bookList) {
            BookDto bookDto = new BookDto();
            bookDto.setTitle(book.getTitle());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setAvailableCount(book.getAvailableCount());
            bookDto.setPageCount(book.getPageCount());
            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }
}
