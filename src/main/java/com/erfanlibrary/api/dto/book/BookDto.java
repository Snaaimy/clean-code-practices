package com.erfanlibrary.api.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookDto {
    private String title;
    private String author;
    private Integer pageCount;
    private Integer availableCount;
}
