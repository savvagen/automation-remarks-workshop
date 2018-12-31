package com.example.java;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.pages.CatalogPage;
import com.example.java.models.kiss_pattern.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;



@Epic("SmokeTests")
public class BaseTest {


    @RegisterExtension public MainPage mainPage = new MainPage();
    @RegisterExtension public CatalogPage catalogPage = new CatalogPage();

    @BeforeAll
    static void setUp() {
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


    public void addAuthCookiesForUser(User user) throws UnsupportedEncodingException {
        // Made login request and extract cookies from RestAssured
        Response r = given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user.username + ":" + user.password).getBytes("utf-8")))
                .when().get("/login");
        io.restassured.http.Cookie cookie1 = r.getDetailedCookie("_TRAEFIK_BACKEND");
        io.restassured.http.Cookie cookie2 = r.getDetailedCookie("md.sid");
        io.restassured.http.Cookie cookie3 = r.getDetailedCookie("logged_in");
        // Create selenium cookies
        Cookie sCookie1 = new Cookie.Builder(cookie1.getName(), cookie1.getValue()).domain(cookie1.getDomain()).path(cookie1.getPath()).isHttpOnly(cookie1.isHttpOnly()).isSecure(cookie1.isSecured()).build();
        Cookie sCookie2 = new Cookie.Builder(cookie2.getName(), cookie2.getValue()).domain(cookie2.getDomain()).path(cookie2.getPath()).isHttpOnly(cookie2.isHttpOnly()).isSecure(cookie2.isSecured()).build();
        Cookie sCookie3 = new Cookie.Builder(cookie3.getName(), cookie3.getValue()).domain(cookie3.getDomain()).path(cookie3.getPath()).isHttpOnly(cookie3.isHttpOnly()).isSecure(cookie3.isSecured()).build();
        // Add cookies to Selenium
        Selenide.clearBrowserCookies();
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().addCookie(sCookie1);
        getWebDriver().manage().addCookie(sCookie2);
        getWebDriver().manage().addCookie(sCookie3);
    }

}
