package com.example.groovy.services

import com.example.java.models.Customer;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CustomerService {

    def requestSpec
    def defaultContentType = "application/json; charset=utf-8"

    CustomerService(String basePath){
        this.requestSpec = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .basePath(basePath)
                .log().all()
    }

    @Step
    def getCustomers(){
        requestSpec.when()
                .get("customers")
                .then().log().all()
    }

    @Step
    def getCustomer(String id){
        requestSpec.when()
                .get("customers/" + id)
                .then().log().all()
    }

    @Step
    public ValidatableResponse deleteCustomer(String id){
        requestSpec.when()
                .delete("customers/" + id)
                .then().log().all()
    }


    @Step
    def registerCustomer(Customer customer){
        requestSpec.when().body(customer)
                .post("register")
                .then().log().all()
    }





}