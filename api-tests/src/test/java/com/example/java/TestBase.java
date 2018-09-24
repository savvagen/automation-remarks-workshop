package com.example.java;

import com.example.java.models.Customer;
import com.example.java.services.UserApiService;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBase {

    public static UserApiService userApiService;

    @BeforeAll
    static void setUpServices(){
        baseURI = "http://35.232.243.253";
        userApiService = new UserApiService("/");

    }


}
