package com.example.java;

import com.example.models.Customer;
import com.example.services.CustomerService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBase {

    public static CustomerService customerService;
    public static Customer account;


    @BeforeAll
    static void setUpServices(){
        baseURI = "http://35.232.243.253";
        customerService = new CustomerService("/");

        account = new Customer()
                .setFirstName("Savva")
                .setLastName("Genchevskiy")
                .setUsername("savva.genchevskiy")
                .setEmail(System.getenv("ACCOUNT_EMAIL"))
                .setPassword(System.getenv("ACCOUNT_PASSWORD"));

    }


}
