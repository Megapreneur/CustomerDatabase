package com.megapreneur.customerdatabase.service;

import com.megapreneur.customerdatabase.dto.AddUserRequest;
import com.megapreneur.customerdatabase.exception.CustomerDatabaseException;
import com.megapreneur.customerdatabase.model.data.BillingDetails;
import com.megapreneur.customerdatabase.model.data.Customer;
import com.megapreneur.customerdatabase.model.repositories.BillingDetailsRepository;
import com.megapreneur.customerdatabase.model.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final BillingDetailsRepository billingDetailsRepository;
    @Override
    @Transactional
    public void addUser(AddUserRequest addUserRequest) throws CustomerDatabaseException {
        if (customerRepository.existsByEmail(addUserRequest.getEmail().toLowerCase())) throw new CustomerDatabaseException("A customer with email address " +addUserRequest.getEmail()+ " already Exist!!!");
        if (billingDetailsRepository.existsByAccountNumber(addUserRequest.getAccountNumber()+ "-01")) throw new CustomerDatabaseException("A customer with this account number already exist");
        if (!isValidAccountNumber(addUserRequest.getAccountNumber())) throw new CustomerDatabaseException("The account number is not valid");

        Customer newCustomer = new Customer();
        BillingDetails customerBillingDetails = new BillingDetails();

        newCustomer.setFirstName(addUserRequest.getFirstName());
        newCustomer.setLastName(addUserRequest.getLastName());
        newCustomer.setEmail(addUserRequest.getEmail().toLowerCase());

        customerBillingDetails.setAccountNumber(addUserRequest.getAccountNumber()+ "-01");
        customerBillingDetails.setTariff(addUserRequest.getTariff());

        newCustomer.setBillingDetails(customerBillingDetails);

        billingDetailsRepository.save(customerBillingDetails);
        customerRepository.save(newCustomer);
    }

    @Override
    public Customer getACustomer(String email) throws CustomerDatabaseException {
        return customerRepository.findByEmail(email.toLowerCase()).orElseThrow(() ->
                new CustomerDatabaseException("Customer with the email does not exist"));
    }

    @Override
    public Page<Customer> getAllCustomer(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        return customerRepository.findAll(pageRequest);
    }
    private static boolean isValidAccountNumber(String accountNumber){
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(accountNumber);
        return matcher.matches();
    }
}
