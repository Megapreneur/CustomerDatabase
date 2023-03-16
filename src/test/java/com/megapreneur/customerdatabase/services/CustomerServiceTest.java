package com.megapreneur.customerdatabase.services;

import com.megapreneur.customerdatabase.dto.AddUserRequest;
import com.megapreneur.customerdatabase.exception.CustomerDatabaseException;
import com.megapreneur.customerdatabase.model.data.Customer;
import com.megapreneur.customerdatabase.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;

    @Test
    @DisplayName("Test to add new Customer")
    void testThatANewCustomerCanBeAdded() throws CustomerDatabaseException {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Chiamaka");
        addUserRequest.setLastName("Ngozi");
        addUserRequest.setEmail("amakaf@GMAIL.COM");
        addUserRequest.setAccountNumber("0264667891");
        addUserRequest.setTariff("VIP");
        customerService.addUser(addUserRequest);
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));
    }
    @Test
    @DisplayName("Test that email is unique")
    void testThatEmailIsUnique() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Seun");
        addUserRequest.setLastName("Adams");
        addUserRequest.setEmail("DAN@GMAIL.COM");
        addUserRequest.setAccountNumber("1234543210");
        addUserRequest.setTariff("VIP");
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));

    }
    @Test
    @DisplayName("Test that account number is unique")
    void testThatCustomerAccountNumberIsUnique() throws CustomerDatabaseException {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Dan");
        addUserRequest.setLastName("Toye");
        addUserRequest.setEmail("DANJ@GMAIL.COM");
        addUserRequest.setAccountNumber("1234567891");
        addUserRequest.setTariff("VIP");
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));

    }
    @Test
    @DisplayName("Test that account numbers can only be numerical")
    void testThatCustomerAccountNumberCanOnlyBeNumerical() throws CustomerDatabaseException {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Alex");
        addUserRequest.setLastName("Toye");
        addUserRequest.setEmail("alex@GMAIL.COM");
        addUserRequest.setAccountNumber("12345678gh");
        addUserRequest.setTariff("VIP");
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));


    }
    @Test
    @DisplayName("Test that the length of account number cannot be more than 10")
    void testThatCustomerAccountNumberCannotBeMoreThan10() throws  CustomerDatabaseException {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Segun");
        addUserRequest.setLastName("Tunde");
        addUserRequest.setEmail("st@GMAIL.COM");
        addUserRequest.setAccountNumber("123456787747727");
        addUserRequest.setTariff("VIP");
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));


    }
    @Test
    @DisplayName("Test that the length of account number cannot be less than 10")
    void testThatCustomerAccountNumberCannotBeLessThan10() throws CustomerDatabaseException {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Uche");
        addUserRequest.setLastName("Onuha");
        addUserRequest.setEmail("uo@GMAIL.COM");
        addUserRequest.setAccountNumber("7747727");
        addUserRequest.setTariff("VIP");
        assertThrows(CustomerDatabaseException.class, () -> customerService.addUser(addUserRequest));

    }
    @Test
    @DisplayName("Test that we can find a customer by their email address")
    void testThatACustomerCanBeFoundUsingTheirEmail() throws CustomerDatabaseException {
        Customer savedCustomer = customerService.getACustomer("DAN@GMAIL.COM");
        assertEquals("Daniel", savedCustomer.getFirstName());
    }
    @Test
    @DisplayName("Test that we can get all customers")
    void testThatWeCanGetAllCustomers(){
        Page<Customer> customers = customerService.getAllCustomer(1,1);
        assertEquals(5, customers.getTotalElements());
    }

}
