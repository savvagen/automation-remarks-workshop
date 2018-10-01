package com.example.java;

import com.example.java.assertions.AssertableResponse;
import com.example.java.extensions.annotations.TestOnLinux;
import com.example.java.extensions.listeners.TestLoggingListener;
import com.example.java.extensions.annotations.Negative;
import com.example.java.extensions.annotations.Positive;
import com.example.java.TestBase;
import com.example.java.models.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.java.conditions.Conditions.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@Tag("registration-tests")
@DisplayName("Registration tests")
@ExtendWith(TestLoggingListener.class)
public class RegistrationTests extends TestBase {


    // Api tests example with Matchers

    public User usUser;
    public User ukUser;


    @BeforeAll
    static void steUpTest(){
        List<Map> customers = userApiService.getCustomers().response.extract().body()
                .jsonPath().param("userName", "savva.genchevskiy")
                .get("_embedded.customer.findAll { customer -> customer.username != userName }");
        customers.forEach((customer)-> userApiService.deleteCustomer(customer.get("id").toString()));
    }

    @BeforeEach
    void setUpTest(){
        ukUser = new User().setFirstName(ukFaker.name().firstName())
                .setLastName(ukFaker.name().lastName())
                .setUsername(ukFaker.name().nameWithMiddle())
                .setEmail(ukFaker.internet().emailAddress())
                .setPassword(ukFaker.internet().password())
                .setId(ukFaker.internet().uuid());
        usUser = new User().setFirstName(usFaker.name().firstName())
                .setLastName(usFaker.name().lastName())
                .setUsername(usFaker.name().username())
                .setEmail(usFaker.internet().emailAddress())
                .setPassword(usFaker.internet().password())
                .setId(usFaker.internet().uuid());
    }


    @AfterAll
    static void tearDownTests(){
        List<Map> customers = userApiService.getCustomers().response.extract().body()
                .jsonPath().param("userName", "savva.genchevskiy")
                .get("_embedded.customer.findAll { customer -> customer.username != userName }");
        customers.forEach((customer)-> userApiService.deleteCustomer(customer.get("id").toString()));
        /*List<Map> customers = userApiService.getCustomers().response.extract().body()
                .jsonPath().get("_embedded.customer.findAll()");
        customers.forEach((customer)-> userApiService.deleteCustomer(customer.get("id").toString()));*/
    }





    @Positive
    @TestOnLinux
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*") // Only on 64 Bit architectures
    @DisplayName("Register user form United States")
    void registerUkCustomer(){
        userApiService.registerCustomer(usUser)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", notNullValue()))
                .shouldHave(body("id", containsString("0")))
                .shouldHave(body(matchesJsonSchemaInClasspath("json/registered.json")));
    }


    @Positive
    @ParameterizedTest
    @DisplayName("Register user from multiply regions")
    @ValueSource(strings = {"ru", "uk", "bg", "de", "en", "en-US", "en-ZA"})
    void registerRusCustomersFromDifferentRegions(String region){
        Faker f = new Faker(new Locale(region));
        User user = new User().setUsername(f.name().username())
                .setEmail(f.internet().emailAddress())
                .setPassword(f.internet().password());
        userApiService.registerCustomer(user)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", notNullValue()))
                .shouldHave(body("id", containsString("0")))
                .shouldHave(body(matchesJsonSchemaInClasspath("json/registered.json")));
    }





    @Positive
    @ParameterizedTest(name = "Register wit invalid data.")
    @CsvFileSource(resources = "/test_data/inavlid-reg-data.csv", numLinesToSkip = 1)
    void registerUsersFromCsvSource(String id, String firstName, String lastName, String userName, String email, String password){
        User user = new User().setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(userName)
                .setEmail(email)
                .setPassword(password);
        userApiService.registerCustomer(user)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", notNullValue()))
                .shouldHave(body("id", containsString("0")))
                .shouldHave(body(matchesJsonSchemaInClasspath("json/registered.json")));
    }



    @Negative
    @Test
    @DisplayName("Register existing user")
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void registerCusotmerWithExistingAccount(){
        userApiService.registerCustomer(usUser)
                .shouldHave(statusCode(200));
        AssertableResponse r = userApiService.registerCustomer(usUser)
                .shouldHave(statusCode(500))
                .shouldHave(contentType("text/plain; charset=utf-8"));
        assertAll("Invalid response validation:",
                () -> assertEquals(500, r.response.extract().statusCode()),
                () -> assertEquals("text/plain; charset=utf-8", r.response.extract().contentType()),
                () -> assertEquals(1, userApiService.getCustomers().response.extract().body()
                        .jsonPath().param("userName", usUser.username)
                        .getList("_embedded.customer.findAll { customer -> customer.username == userName }").size())
        );
    }


    @ParameterizedTest(name = "Register wit invalid data.")
    @Tag("negative")
    @MethodSource("com.example.java.data_providers.UsersDataProvider#invalidRegistrationData")
    void registerCustomerWithInvalidData(User user){
        userApiService.registerCustomer(user)
                .shouldHave(statusCode(500))
                .shouldHave(contentType("text/plain; charset=utf-8"))
                .shouldHave(body(is("")));
    }


    @Negative
    @ParameterizedTest(name = "Register wit invalid data.")
    @MethodSource("com.example.java.data_providers.UsersDataProvider#invalidUserRegistrationData")
    void registerWithInvalidUsers(int statusCode, String contentType, User user){
        userApiService.registerCustomer(user)
                .shouldHave(statusCode(statusCode))
                .shouldHave(contentType(contentType))
                .shouldHave(body(is("")));
    }




}
