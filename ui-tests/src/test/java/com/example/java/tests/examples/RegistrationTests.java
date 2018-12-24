package com.example.java.tests.examples;

import com.example.java.BaseTest;
import com.example.java.models.User;
import com.example.java.models.elements_pattern.pages.MainPage.elements.RegisterModal;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Tag("Example")
public class RegistrationTests extends BaseTest {


    @Test
    void shouldRegisterUser() throws Exception {

        // given
        at(MainPage.class).open().register();

        // when
        at(RegisterModal.class).registerWith(new User());

        // then
        at(MainPage.class).header.userNameButton.shouldBe(visible);

    }


}
