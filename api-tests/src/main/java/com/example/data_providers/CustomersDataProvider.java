package com.example.data_providers;

import com.example.models.Customer;

import java.util.stream.Stream;

public class CustomersDataProvider {


    static Stream<Customer> registrationData() {
        return Stream.of(
                new Customer("test", "test", null, "test@mail.com", "test1234", null),
                new Customer("test", "test", "", "test@mail.com", "test1234", null),
                new Customer("test", "test", "test.test", null, "test1234", null),
                new Customer("test", "test", "test.test", "", "test1234", null),
                new Customer("test", "test", "test.test", "test@@@mail.com", "test1234", null),
                new Customer("test", "test", "test.test", "test@@@mail.com", null, null),
                new Customer("test", "test", "test.test", "test@@@mail.com", "", null),
                new Customer("test", "test", "test.test", "test@@@mail.com", "123", null),
                new Customer("", "", "", "", "", null),
                new Customer(null, null, null, null, null, null)
        );
    }



}
