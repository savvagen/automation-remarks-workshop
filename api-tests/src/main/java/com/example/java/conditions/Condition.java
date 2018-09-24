package com.example.java.conditions;

import io.restassured.response.ValidatableResponse;

import javax.validation.Valid;

public interface Condition {

    void check(ValidatableResponse response);


}
