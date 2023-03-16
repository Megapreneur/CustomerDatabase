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
//    @Query("SELECT new com.megapreneur.customerdatabase.dto.CustomerDto( c.firstName, c.lastName, c.email, bd.accountNumber ,bd.tariff) from Customer c left join c.billingDetails bd")
    Optional<Customer> findByEmail(String email);
}
