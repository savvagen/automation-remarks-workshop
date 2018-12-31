package com.example.groovy

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.logevents.SelenideLogger
import com.example.java.models.User
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

import static com.codeborne.selenide.Condition.*

class LoginTests {

    static <T> T at(Class<T> tClass) { tClass.newInstance() }

    @RegisterExtension
    def user = new User(
            firstName: "Savva",
            lastName: "Genchevskiy",
            username: "savva.gench",
            password: "s.g19021992")


    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide())
        Configuration.baseUrl = "http://35.232.243.253"
        Configuration.timeout = 8000
    }



    @Test
    @DisplayName("Positive login tests")
    void positiveLogin(){
        at(MainPage.class).open().loginAs(user)
                .with {
                    loginModal.loginMessage.shouldBe(visible)
                        .shouldHave text("Login successful.")
                    userNameButton.shouldBe(visible)
                        .shouldHave text("Logged in as ${user.getFirstName()} ${user.getLastName()}")
                    shouldBeOpened()
        }
    }


}
