package com.example.java.registration;


import com.example.java.conditions.Conditions;
import com.example.java.extensions.listeners.TestLoggingListener;
import com.example.java.extensions.tags.Negative;
import com.example.java.extensions.tags.Positive;
import com.example.java.TestBase;
import com.example.java.models.Customer;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.java.conditions.Conditions.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.junit.jupiter.api.Assertions.*;



import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


@Tag("registration-tests")
@ExtendWith(TestLoggingListener.class)
class RegistrationTests extends TestBase {


    @BeforeAll
    void steUpTest(){
        /*List<Map> customers = userApiService.getCustomers().extract().body()
                .jsonPath().param("userName", account.username)
                .get("_embedded.customer.findAll { customer -> customer.username == userName }");
        customers.forEach((customer)-> userApiService.deleteCustomer(customer.get("id").toString()));*/
    }

    @AfterAll
    void tearDownTests(){
        List<Map> customers = userApiService.getCustomers().response.extract().body()
                .jsonPath().get("_embedded.customer.findAll()");
        customers.forEach((customer)-> userApiService.deleteCustomer(customer.get("id").toString()));
    }

    private Faker faker = new Faker(new Locale("ru"));


    @Test
    @DisplayName("Positive registration")
    @Positive
    void registerCustomer(){
        Customer user = new Customer()
                .setUsername(faker.name().username())
                .setEmail("");

        userApiService.registerCustomer(user)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", notNullValue()));
                //.shouldHave(body("id", not(null), "id_value"))
                //.shouldHave(body(matchesJsonSchemaInClasspath("")));

    }


    /*@Test
    @DisplayName("Register existing account")
    @Negative
    void registerCustomerWithSameAccountCreds(){
        userApiService.registerCustomer(account);
        ValidatableResponse r = userApiService.registerCustomer(account);
        assertAll("validate failed: 500 response",
                () -> assertEquals(500, r.extract().statusCode()),
                () -> assertEquals("text/plain; charset=utf-8", r.extract().contentType()),
                () -> assertEquals(1, userApiService.getCustomers().extract().body()
                        .jsonPath().param("userName", account.username)
                        .getList("_embedded.customer.findAll { customer -> customer.username == userName }").size())
        );
    }

    @ParameterizedTest(name = "Register wit invalid data.")
    @Negative
    @MethodSource("com.example.java.data_providers.CustomersDataProvider#registrationData")
    void registerCustomerWithInvalidData(Customer account){
        ValidatableResponse r = userApiService.registerCustomer(account);
        assertAll("Verify that response is 400 or 500:",
                () -> assertEquals(500, r.extract().statusCode()),
                () -> assertEquals("text/plain; charset=utf-8", r.extract().contentType())
        );
    }*/






}
