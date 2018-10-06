package com.example.java.services;


import com.example.java.assertions.AssertableResponse;
import com.example.java.models.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogApiService {

    RequestSpecification requestSpec;
    public final String defaultContentType = "application/json; charset=utf-8";

    public CatalogApiService(String basePath){
        this.requestSpec = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured())
                .basePath(basePath);
    }


    public AssertableResponse getCatalog(){
        return new AssertableResponse(requestSpec.when()
                .get("/")
                .then());
    }

    public AssertableResponse getCatalogItem(String id){
        return new AssertableResponse(requestSpec.when()
                .get("/" + id)
                .then());
    }

    public AssertableResponse getCatalogSize(){
        return new AssertableResponse(requestSpec.when()
                .get("/size")
                .then());
    }



}
