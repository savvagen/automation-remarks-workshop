package com.example.groovy


import com.example.java.TestBase
import org.junit.jupiter.api.*

@Tag("registration-tests")
class RegistrationTests extends TestBase {

/*

    @BeforeEach
    void steUpTest(){
        */
/*List<Map> customers = customerService.getCustomers().extract().body()
                .jsonPath().param("userName", account.username)
                .get("_embedded.customer.findAll { customer -> customer.username == userName }");
        customers.forEach((customer)-> customerService.deleteCustomer(customer.get("id").toString()));*//*

    }

    @AfterEach
    void tearDownTests(){
        List<Map> customers = customerService.getCustomers().extract().body()
                .jsonPath().param("userName", account.username)
                .get("_embedded.customer.findAll()");
        customers.forEach((customer)-> customerService.deleteCustomer(customer.get("id").toString()));
    }



    @Test
    @DisplayName("Positive registration")
    @Positive
    void registerCustomer(){
        ValidatableResponse r = customerService.registerCustomer(account);
        assertEquals(200, r.extract().statusCode());
        assertEquals(customerService.defaultContentType, r.extract().contentType());
        assertFalse(r.extract().body().asString().isEmpty());
        assertNotEquals(null, r.extract().body().jsonPath().get("id"));
    }


    @Test
    @DisplayName("Register existing account")
    @Negative
    void registerCustomerWithSameAccountCreds(){
        customerService.registerCustomer(account);
        ValidatableResponse r = customerService.registerCustomer(account);
        assertAll("validate failed: 500 response",
                () -> assertEquals(500, r.extract().statusCode()),
                () -> assertEquals("text/plain; charset=utf-8", r.extract().contentType()),
                () -> assertEquals(1, customerService.getCustomers().extract().body()
                .jsonPath().param("userName", account.username)
                .getList("_embedded.customer.findAll { customer -> customer.username == userName }").size())
        );
    }

    @ParameterizedTest(name = "Register wit invalid data.")
    @Negative
    @MethodSource("com.example.java.data_providers.CustomersDataProvider#registrationData")
    void registerCustomerWithInvalidData(Customer account){
        ValidatableResponse r = customerService.registerCustomer(account);
        assertAll("Verify that response is 400 or 500:",
                () -> assertEquals(500, r.extract().statusCode()),
                () -> assertEquals("text/plain; charset=utf-8", r.extract().contentType())
        );
    }


*/




}