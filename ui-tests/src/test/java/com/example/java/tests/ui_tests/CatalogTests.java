package com.example.java.tests.ui_tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.java.BaseTest;
import com.example.java.annotations.ServiceParameterExtensions;
import com.example.java.annotations.TestLJ8;
import com.example.java.annotations.TestLoggingExtensions;
import com.example.java.extension_parameters.Service;
import com.example.java.extension_parameters.SiteServiceExtension;
import com.example.java.models.User;
import com.example.java.services.UserApiService;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.*;


@Feature("catalog")
@Tag("catalog-ui")
@ServiceParameterExtensions
@TestLoggingExtensions
//@Execution(ExecutionMode.SAME_THREAD)
public class CatalogTests extends BaseTest {



    @RegisterExtension private static User user = new User()
            .setFirstName("Savva")
            .setLastName("Genchevskiy")
            .setEmail("savva.genchev@gmail.com")
            .setUsername("savva.genchev").setPassword("s.g19021992");



    @BeforeAll
    static void setUpTests(Service service){
        service.registerCustomer(user);
    }

    @BeforeEach
    void setUpTest() throws Exception {
        mainPage.open();
        addAuthCookiesForUser(user);
    }

    @AfterEach
    void tearDownTest(){
        clearBrowserCache();
    }

    @AfterAll
    static void tearDownTests(@SiteServiceExtension.WebService UserApiService service) {
        service.deleteCustomerWithName(user.username);
    }


    @TestLJ8
    void shouldFilterProductsByColor(){
        catalogPage.open().filters.first().shouldBe(visible);
        catalogPage.filterBy("brown").clearButton.shouldBe(visible);
        catalogPage.products.first().shouldBe(visible);
        catalogPage.products.filterBy(visible).shouldHaveSize(3);
    }

    @ParameterizedTest
    @MethodSource("com.example.java.test_data.CatalogData#catalogProductNumberData")
    void shouldShowDifferentNumberOfTheProducts(int number, String urlParams){
        catalogPage.open().productNumbers.first().shouldBe(visible);
        catalogPage.showProducts(number);
        assertAll("check product size to be " + String.valueOf(number),
                () -> assertTrue(getWebDriver().getCurrentUrl().contains(urlParams)),
                () -> assertEquals(catalogPage.products.filterBy(visible).size(), number)
        );

    }







}
