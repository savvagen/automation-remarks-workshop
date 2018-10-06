package com.example.java.services;


import com.example.java.assertions.AssertableResponse;
import com.example.java.models.Address;
import com.example.java.models.Card;
import com.example.java.models.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .filter(new AllureRestAssured())
                .basePath(basePath);
    }


    public AssertableResponse login(){
        return new AssertableResponse(requestSpec.when()
                .get("/login")
                .then());
    }


    public AssertableResponse getCustomers(){
        log.info("get customers.");
        return new AssertableResponse(requestSpec.when()
                .get("customers")
                .then());
    }

    public AssertableResponse getCustomer(String id){
        return new AssertableResponse(requestSpec.when()
                .get("customers/" + id)
                .then());
    }

    public AssertableResponse deleteCustomer(String id){
        return new AssertableResponse(requestSpec.when()
                .delete("customers/" + id)
                .then());
    }

    public AssertableResponse registerCustomer(User user){
        log.info("register user {}", user);
        return new AssertableResponse(requestSpec.when()
                .body(user)
                .post("register")
                .then());
    }


    public AssertableResponse getCards(){
        return new AssertableResponse(requestSpec.when()
                .get("/cards")
                .then());
    }

    public AssertableResponse getCard(String cardId){
        return new AssertableResponse(requestSpec.when()
                .get("/cards/" + cardId)
                .then());
    }

    public AssertableResponse addCard(Card card){
        return new AssertableResponse(requestSpec.when()
                .body(card)
                .post("/cards")
                .then());
    }

    public AssertableResponse deleteCard(String cardId){
        return new AssertableResponse(requestSpec.when()
                .delete("/cards/" + cardId)
                .then());
    }


    public AssertableResponse getAddresses() {
        return new AssertableResponse(requestSpec.when()
                .get("/addresses")
                .then());
    }

    public AssertableResponse getAddress(String id) {
        return new AssertableResponse(requestSpec.when()
                .get("/addresses/" + id)
                .then());
    }


    public AssertableResponse addAddress(Address address) {
        return new AssertableResponse(requestSpec.when()
                .body(address)
                .post("/addresses")
                .then());
    }


    public AssertableResponse deleteAddress(String id) {
        return new AssertableResponse(requestSpec.when()
                .delete("/addresses/" + id)
                .then());
    }



}
