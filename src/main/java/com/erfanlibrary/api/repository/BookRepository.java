package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findOneById(Integer id);

}
