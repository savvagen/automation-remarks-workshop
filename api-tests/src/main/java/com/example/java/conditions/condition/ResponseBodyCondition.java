package com.example.java.conditions.condition;

import com.example.java.conditions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

@AllArgsConstructor
public class ResponseBodyCondition implements Condition {

    private String path;
    private Matcher matcher;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().body(path, matcher);
    }

    @Override
    public String toString() {
        return "Json body field \'" + path +"\' should match the condition: " + matcher;
    }


}

