package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.CustomerBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBookRepository extends JpaRepository<CustomerBook, Long> {
}
