package com.example.java.conditions.condition;


import com.example.java.conditions.Condition;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContentTypeCondition implements Condition {

    private String contentType;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().contentType(contentType);
    }

    @Override
    public String toString() {
        return "Content Type: " + contentType;
    }

}
