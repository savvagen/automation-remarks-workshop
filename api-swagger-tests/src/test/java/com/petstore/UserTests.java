package com.petstore;

import com.petstore.api.UserApiService;
import com.petstore.client.model.User;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class UserTests {

    private UserApiService userService = new UserApiService();

    @Test
    public void shouldCreateNewUser(){
        // Given
        User user = new User().username("savva.gen")
                .email("genchevskiy.test@gmail.com")
                .password("s.g19021992");

        userService.createUser(user);

        User userByName = userService.getUserByName(user.getUsername()).as(User.class);

        Assertions.assertEquals(userByName.getUsername(), user.getUsername());
        Assertions.assertEquals(userByName.getEmail(), user.getEmail());

    }

    @Test
    public void shouldDeleteUser(){
        // Given
        User user = new User().username("savva.gen")
                .email("genchevskiy.test@gmail.com")
                .password("s.g19021992");

        userService.createUser(user);

        userService.deleteUser(user.getUsername());
        User userByName = userService.getUserByName(user.getUsername()).as(User.class);


        Assertions.assertEquals(userByName.getUsername(), user.getUsername());

    }

}
