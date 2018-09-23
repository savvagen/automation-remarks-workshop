package com.example.services;


import com.example.models.Customer;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CustomerService {

    RequestSpecification requestSpec;
    public final String defaultContentType = "application/json; charset=utf-8";

    public CustomerService(String basePath){
        this.requestSpec = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .basePath(basePath)
                .log().all();
    }

    @Step
    public ValidatableResponse getCustomers(){
        return this.requestSpec.when()
                .get("customers")
                .then().log().all();
    }

    @Step
    public ValidatableResponse getCustomer(String id){
        return this.requestSpec.when()
                .get("customers/" + id)
                .then().log().all();
    }

    @Step
    public ValidatableResponse deleteCustomer(String id){
        return this.requestSpec.when()
                .delete("customers/" + id)
                .then().log().all();
    }


    @Step
    public ValidatableResponse registerCustomer(Customer customer){
        return this.requestSpec.when()
                .body(customer)
                .post("register")
                .then().log().all();
    }





}
