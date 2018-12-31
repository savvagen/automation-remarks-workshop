package com.example.java.tests.ui_tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.java.BaseTest;
import com.example.java.extension_parameters.Service;
import com.example.java.extension_parameters.ServiceParameterResolver;
import com.example.java.extension_parameters.SiteServiceExtension;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.pages.MainPage;
import com.example.java.services.UserApiService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Condition.*;
import static com.example.java.extension_parameters.SiteServiceExtension.*;



@Feature("login")
@Tag("login-ui")
//Register @WebService and Service parameters for tests and before & after conditions
@ExtendWith({ScreenShooterExtension.class, TextReportExtension.class, SiteServiceExtension.class, ServiceParameterResolver.class})
//@Execution(ExecutionMode.SAME_THREAD)
public class LoginTests extends BaseTest {


    @RegisterExtension public static User user = new User()
            .setFirstName("Savva")
            .setLastName("Genchevskiy")
            .setEmail("savva.gench@gmail.com")
            .setUsername("savva.gench").setPassword("s.g19021992");



    @BeforeAll
    static void setUpTests(Service service){
        service.registerCustomer(user);
    }

    @BeforeEach
    void setUpTest(){
        mainPage.open();
    }

    @AfterEach
    void tearDownTest(){
        if (mainPage.logoutButton.is(visible)){
            mainPage.logOut();
        }
    }

    @AfterAll
    static void tearDownTests(@WebService UserApiService service) {
        service.deleteCustomerWithName(user.username);
    }



    @Test
    @Tag("LoginTests")
    void loginWithValidCredentials(){
        mainPage.login().loginAs(user);
        mainPage.loginModal.message.shouldBe(visible);
        mainPage.loginModal.message.shouldHave(text("Login successful."));
        mainPage.accountButton.shouldBe(visible).shouldHave(text("Logged in as " + user.firstName + " " + user.lastName));
    }


    @ParameterizedTest
    @Tag("LoginTests")
    @MethodSource("com.example.java.test_data.LoginData#invalidLoginData")
    void unableToLoginWithInvalidCredentials(User invalidUser, String errorMessage){
        mainPage.login().loginAs(invalidUser);
        mainPage.loginModal.message.shouldBe(visible);
        mainPage.loginModal.message.shouldHave(text(errorMessage));

    }





}
