package com.megapreneur.customerdatabase.controller;

import com.megapreneur.customerdatabase.dto.AddUserRequest;
import com.megapreneur.customerdatabase.dto.CustomerDto;
import com.megapreneur.customerdatabase.exception.*;
import com.megapreneur.customerdatabase.model.data.Customer;
import com.megapreneur.customerdatabase.service.CustomerService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.annotations.reflection.internal.XMLContext;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerDbController {
    private final CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> addCustomer(@RequestBody AddUserRequest addUserRequest) throws CustomerDatabaseException {
        customerService.addUser(addUserRequest);
        return ResponseEntity.ok("Successful");
    }
    @GetMapping("/find-customer")
    public ResponseEntity<?> findACustomer(@RequestParam(value = "email") String email) throws CustomerDatabaseException {
        Customer customer = customerService.getACustomer(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @GetMapping("/get-all-customer")
    public ResponseEntity<?> getAllCustomer(@RequestParam(defaultValue = "1", required = false) int page, @RequestParam(defaultValue = "1", required = false) int limit){
        Page<Customer> customers = customerService.getAllCustomer(page, limit);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
