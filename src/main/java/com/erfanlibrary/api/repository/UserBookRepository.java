package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {
}
