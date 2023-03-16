package com.megapreneur.customerdatabase.service;

import com.megapreneur.customerdatabase.dto.AddUserRequest;
import com.megapreneur.customerdatabase.exception.*;
import com.megapreneur.customerdatabase.model.data.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    void addUser(AddUserRequest addUserRequest) throws CustomerDatabaseException;
    Customer getACustomer(String email) throws  CustomerDatabaseException;
    Page<Customer> getAllCustomer(int page, int limit);
}
