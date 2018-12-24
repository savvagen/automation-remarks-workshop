package com.example.java.tests.examples;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.java.BaseTest;
import com.example.java.annotations.TestLJ8;
import com.example.java.extension_parameters.Service;
import com.example.java.extension_parameters.ServiceParameterResolver;
import com.example.java.listeners.TestLifecycleLogger;
import com.example.java.listeners.TimeExecutionLogger;
import com.example.java.models.User;
import com.example.java.extension_parameters.SiteServiceExtension;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import com.example.java.models.elements_pattern.pages.MainPage.elements.LoginModal;
import com.example.java.services.UserApiService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.codeborne.selenide.Condition.*;
import static com.example.java.extension_parameters.SiteServiceExtension.*;



@ExtendWith({ScreenShooterExtension.class, TextReportExtension.class})

@ExtendWith({ServiceParameterResolver.class})

@ExtendWith(SiteServiceExtension.class)

@Tag("Example")
public class LoginTests extends BaseTest implements TestLifecycleLogger, TimeExecutionLogger {

    @RegisterExtension
    public MainPage mainPage = new MainPage();

    @RegisterExtension
    public User user = new User().setFirstName("Savva").setLastName("Genchevskiy")
            .setEmail("savva.genchevskiy@gmail.com").setUsername("savva.gench").setPassword("s.g19021992");


    @BeforeAll
    void setUpTest(@WebService UserApiService service){
        service.registerCustomer(user);
    }

    @AfterAll
    void tearDownTest(Service service){
        if (mainPage.header.accountButton.is(visible)) {
            mainPage.logout();
        }
        service.deleteCustomersBesides(user.username);

    }




    @TestLJ8
    void loginTest(){
        mainPage.open()
                .login()
                .loginWith(user)
                .loginModal.successMessage.shouldBe(visible);
        mainPage.header.userNameButton.shouldBe(visible)
                .shouldHave(text("Logged in as " + user.getFirstName() + " " + user.getLastName()));
    }


    @Test
    void shouldLoginUser() {
        // given
        at(MainPage.class).open().login();

        // when
        at(LoginModal.class).loginWith(user);

        // then
        at(MainPage.class).header.userNameButton.shouldBe(visible)
                .shouldHave(text("Logged in as " + user.getFirstName() + " " + user.getLastName()));

    }


}
