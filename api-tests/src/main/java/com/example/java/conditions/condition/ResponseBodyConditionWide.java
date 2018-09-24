package com.example.java.conditions.condition;

import com.example.java.conditions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

@AllArgsConstructor
public class ResponseBodyConditionWide implements Condition {

    private String path;
    private Matcher matcher;
    private Object additionalKeyMatcherPairs;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().body(path, matcher, additionalKeyMatcherPairs);
    }

    @Override
    public String toString() {
        return "Json body field \'" + path +"\' should match the condition: " + matcher + ", and should have " + additionalKeyMatcherPairs;
    }


}

