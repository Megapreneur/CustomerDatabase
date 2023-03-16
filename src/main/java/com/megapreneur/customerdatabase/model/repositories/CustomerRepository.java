package com.megapreneur.customerdatabase.model.repositories;

import com.megapreneur.customerdatabase.dto.CustomerDto;
import com.megapreneur.customerdatabase.model.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}
