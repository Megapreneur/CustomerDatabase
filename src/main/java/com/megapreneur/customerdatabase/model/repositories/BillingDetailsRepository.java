package com.megapreneur.customerdatabase.model.repositories;

import com.megapreneur.customerdatabase.model.data.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
    boolean existsByAccountNumber(String accountNumber);
}
