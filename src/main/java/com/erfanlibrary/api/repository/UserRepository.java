package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
