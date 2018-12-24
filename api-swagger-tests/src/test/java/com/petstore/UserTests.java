package com.petstore;

import com.petstore.api.UserApiService;
import com.petstore.client.model.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    private UserApiService userService = new UserApiService();

    @Test
    public void shouldCreateNewUser(){
        // Given
        User user = new User().username("savva.gen")
                .email("genchevskiy.tests@gmail.com")
                .password("s.g19021992");

        userService.createUser(user);

        User userByName = userService.getUserByName(user.getUsername()).as(User.class);

        assertEquals(userByName.getUsername(), user.getUsername());
        assertEquals(userByName.getEmail(), user.getEmail());

    }

    @Test
    public void shouldLoginUser(){
        // Given
        User user = new User().username("savva.gen")
                .email("genchevskiy.tests@gmail.com")
                .password("s.g19021992");

        Response response = userService.loginUser(user);

        assertEquals(response.statusCode(), 200);
        assertTrue(response.body().asString().contains("logged in user"));


    }

}
