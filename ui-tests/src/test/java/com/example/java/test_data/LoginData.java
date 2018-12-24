package com.example.java.test_data;

import com.example.java.models.User;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LoginData {

    public static Stream<Arguments> invalidLoginData(){
        return Stream.of(
                arguments(new User().setUsername("").setPassword("s.g19021992"), "Invalid login credentials."),
                arguments(new User().setUsername("savva.gench").setPassword(""), "Invalid login credentials."),
                arguments(new User().setUsername("savva.gench").setPassword("s.g1902199"), "Invalid login credentials."),
                arguments(new User().setUsername("savva.genc").setPassword("s.g19021992"), "Invalid login credentials.")
        );
    }

}
