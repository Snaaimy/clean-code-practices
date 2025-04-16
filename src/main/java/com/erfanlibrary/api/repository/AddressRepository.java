package com.erfanlibrary.api.repository;

import com.erfanlibrary.api.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
