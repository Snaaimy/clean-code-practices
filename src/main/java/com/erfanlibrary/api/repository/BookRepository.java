package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.model.CustomerBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findOneById(Integer id);

}
