package com.example.groovy

import com.example.groovy.models.Customer
import com.example.groovy.services.CustomerService
import org.junit.jupiter.api.BeforeAll
import static io.restassured.RestAssured.baseURI

class TestBase {

    static def customerService
    static def account


    @BeforeAll
    static void setUpServices(){
        baseURI = "http://35.232.243.253"
        customerService = new CustomerService('/')

        account = new Customer(firstName: '')

                /*.setFirstName("Savva")
                .setLastName("Genchevskiy")
                .setUsername("savva.genchevskiy")
                .setEmail(System.getenv("ACCOUNT_EMAIL"))
                .setPassword(System.getenv("ACCOUNT_PASSWORD"));*/

    }


}
