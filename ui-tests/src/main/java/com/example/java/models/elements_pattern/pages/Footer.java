package com.example.java.models.elements_pattern.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import org.openqa.selenium.By;

public class Footer extends ElementContainer {

    public Footer(String cssSelector) {
        super(cssSelector);
    }

    public Footer(By selector) {
        super(selector);
    }

    public Footer(SelenideElement self) {
        super(self);
    }

}
