package com.example.java.services;


import com.example.java.assertions.AssertableResponse;
import com.example.java.models.Customer;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserApiService {

    RequestSpecification requestSpec;
    public final String defaultContentType = "application/json; charset=utf-8";

    public UserApiService(String basePath){
        this.requestSpec = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .filters(/*new RequestLoggingFilter(), new ResponseLoggingFilter(), */new AllureRestAssured())
                .basePath(basePath);
    }

    @Step
    public AssertableResponse getCustomers(){
        log.info("get customers.");
        return new AssertableResponse(requestSpec.when()
                .get("customers")
                .then());
    }

    @Step
    public AssertableResponse getCustomer(String id){
        return new AssertableResponse(requestSpec.when()
                .get("customers/" + id)
                .then());
    }

    @Step
    public AssertableResponse deleteCustomer(String id){
        return new AssertableResponse(requestSpec.when()
                .delete("customers/" + id)
                .then());
    }


    @Step
    public AssertableResponse registerCustomer(Customer customer){
        log.info("register user {}", customer);
        return new AssertableResponse(requestSpec.when()
                .body(customer)
                .post("register")
                .then());
    }





}
