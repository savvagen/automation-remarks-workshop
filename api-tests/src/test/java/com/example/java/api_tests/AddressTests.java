package com.example.java.api_tests;

import com.example.java.TestBase;
import com.example.java.models.Address;
import org.junit.jupiter.api.Test;
import static com.example.java.conditions.Conditions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;



public class AddressTests extends TestBase {



    @Test
    void addNewAddress(){
        Address address = new Address().setUserID(testUser.id)
                .setCountry("Ukraine")
                .setCity("Odessa")
                .setStreet("Deribasovskaya")
                .setNumber("7").setPostcode("0512314");
        userApiService.addAddress(address).shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", is(nullValue())));
    }

    @Test
    void getAddress(){
        Address address = userApiService.getAddress("57a98ddce4b00679b4a830d1")
                .shouldHave(statusCode(200))
                .response.extract().body().as(Address.class);
        assertAll("Verify address",
                () -> assertEquals("UK", address.country),
                () -> assertEquals("London", address.city));
    }



}
