package com.example.java.tests.ui_tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.java.BaseTest;
import com.example.java.extension_parameters.Service;
import com.example.java.extension_parameters.ServiceParameterResolver;
import com.example.java.extension_parameters.SiteServiceExtension;
import com.example.java.listeners.TestLifecycleLogger;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@ExtendWith({ScreenShooterExtension.class, TextReportExtension.class})
//Register @WebService and Service parameters for tests and before & after conditions
@ExtendWith(SiteServiceExtension.class)
@ExtendWith(ServiceParameterResolver.class)

@Epic("SmokeTests")
@Feature("Registration")
@Tag("RegistrationTests")
//@Execution(ExecutionMode.SAME_THREAD)
public class RegistrationTests extends BaseTest implements TestLifecycleLogger {

    @RegisterExtension public MainPage mainPage = new MainPage();
    @RegisterExtension public static User user = new User()
            .setFirstName("Savva")
            .setLastName("Genchevskiy")
            .setEmail("savva.genchevskiy@gmail.com")
            .setUsername("savva.genchevskiy")
            .setPassword("s.g19021992");


    @BeforeEach
    void setUpTest(Service service){
        service.deleteCustomerWithName(user.username);
        mainPage.open();
    }

    @AfterEach
    void tearDownTest(){
        if (mainPage.logoutButton.is(visible)){
            mainPage.logOut();
        }
    }

    @AfterAll
    static void tearDownTests(Service service) {
        service.deleteCustomerWithName(user.username);
        service.deleteCustomersBesides("savva.gench");
    }


    @Test
    @Tag("LoginTests")
    void registerUserWithValidCredentials(){
        mainPage.register().registerWith(user);
        mainPage.registerModal.message.shouldBe(visible);
        mainPage.registerModal.message.shouldHave(text("Registration and login successful."));
        mainPage.accountButton.shouldBe(visible).shouldHave(text("Logged in as " + user.firstName + " " + user.lastName));
    }

    @ParameterizedTest
    @Tag("LoginTests")
    //@CsvFileSource(resources = "com/example/java/test_data/registrationData.csv", numLinesToSkip = 1)
    @MethodSource("com.example.java.test_data.RegistrationData#invalidRegistrationData")
    void unableToLoginWithInvalidCredentials(User invalidUser, String errorMessage){
        mainPage.register().registerWith(invalidUser);
        mainPage.registerModal.message.shouldBe(visible);
        mainPage.registerModal.message.shouldHave(text(errorMessage));
        mainPage.accountButton.shouldNotBe(visible);
    }


}
