package com.example.java.data_providers;

import com.example.java.models.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UsersDataProvider {

    public static Faker faker = new Faker(new Locale("en-US"));


    static Stream<User> invalidRegistrationData() {
        return Stream.of(
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        "",
                       "",
                        "",
                        null),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        null,
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        null),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        "",
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        null),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        null,
                        faker.internet().password(),
                        faker.internet().uuid()),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        "",
                        faker.internet().password(),
                        faker.internet().uuid()),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        "tests@@@mail.com",
                        faker.internet().password(),
                        faker.internet().uuid()),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        faker.internet().emailAddress(),
                        null,
                        null),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        faker.internet().emailAddress(),
                        "",
                        null),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        faker.internet().emailAddress(),
                        faker.internet().password(1, 4, true),
                        faker.internet().uuid()),
                new User(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(),
                        faker.internet().emailAddress(),
                        faker.internet().password(60, 120, true),
                        faker.internet().uuid()),
                new User("",
                        "",
                        "",
                        "",
                        "",
                        null),
                new User(null,
                        null,
                        null,
                        null,
                        null,
                        null)
        );
    }

    static Stream<Arguments> invalidUserRegistrationData() {
        return Stream.of(
                arguments(200, "text/plain; charset=utf-8", new User(faker.name().firstName(), faker.name().lastName(), null, null, "", null)),
                arguments(200, "text/plain; charset=utf-8", new User("", "", null, null, null, null))
        );
    }


}
