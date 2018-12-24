package com.example.java.models.elements_pattern.pages;

import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class WebPage {

    public String url;
    public String title;

    public Header header = new Header("#navbar");
    public Footer footer = new Footer("#footer");

    public WebPage shouldBeOpened(){
        assertTrue(getWebDriver().getCurrentUrl().contains(url), "Should contain url: " + url);
        return this;
    }

    public WebPage shouldHaveUrl(String url){
        assertEquals(url, getWebDriver().getCurrentUrl(), "Url should equal: " + url);
        return this;
    }



}
