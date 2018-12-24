package com.example.java.extension_parameters;

import com.example.java.assertions.AssertableResponse;
import com.example.java.models.User;

public interface Service {

    AssertableResponse getCustomers();

    AssertableResponse registerCustomer(User user);

    AssertableResponse getCustomer(String id);

    AssertableResponse deleteCustomer(String id);

    void deleteCustomerWithName(String username);

    void deleteAllCustomers();

    void deleteCustomersBesides(String username);

}
