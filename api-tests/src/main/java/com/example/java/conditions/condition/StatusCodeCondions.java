package com.example.java.conditions.condition;

import com.example.java.conditions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondions implements Condition {

    private int statusCode;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().statusCode(statusCode);
    }

    @Override
    public String toString() {
        return "Status code " + statusCode;
    }
}
