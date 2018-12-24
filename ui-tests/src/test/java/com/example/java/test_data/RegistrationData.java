package com.example.java.test_data;

import com.example.java.models.User;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RegistrationData {

    public static Stream<Arguments> invalidRegistrationData(){
        return Stream.of(
                arguments(new User().setUsername("").setEmail("").setPassword("s.g19021992"), "There was a problem with your registration: Internal Server Error"),
                arguments(new User().setUsername("savva.gench").setEmail("test@gmail.com").setPassword(""), "There was a problem with your registration: Internal Server Error"),
                arguments(new User().setUsername("savva.gench").setEmail("test@@mail.com").setPassword(""), "There was a problem with your registration: Internal Server Error"),
                arguments(new User().setUsername("").setEmail("").setPassword(""), "There was a problem with your registration: Internal Server Error")
        );
    }


}
