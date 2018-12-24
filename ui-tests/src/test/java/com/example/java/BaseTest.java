package com.example.java;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class BaseTest {


    @BeforeAll
    static void setUp() throws MalformedURLException {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());

        Configuration.baseUrl = "http://35.232.243.253";
        RestAssured.baseURI = "http://35.232.243.253";

        //Configuration.browser = "chrome";
        //Configuration.browserSize = "1920x1080";

        Configuration.timeout = 8000;
        // setWebDriver(new WebdriverProvider().createDriver(new DesiredCapabilities()));
        // getWebDriver().manage().window().setSize(new Dimension(1920, 1080));
    }


    protected <T> T at(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }


    @AfterAll
    static void tearDown(){
        SelenideLogger.removeListener("allureSelenide");
    }


}
