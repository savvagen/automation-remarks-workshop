package com.example.java.conditions.condition;

import com.example.java.conditions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;


@AllArgsConstructor
public class ResponseBodyConditionJson implements Condition {

    private Matcher matcher;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().body(matcher);
    }

    @Override
    public String toString() {
        return "Json body matches: " + matcher;
    }

}

