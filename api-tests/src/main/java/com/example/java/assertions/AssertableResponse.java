package com.example.java.assertions;

import com.example.java.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    public final ValidatableResponse response;

    @Step("Api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition){
        log.info("Check for " + condition);
        condition.check(response);
        return this;
    }

}
