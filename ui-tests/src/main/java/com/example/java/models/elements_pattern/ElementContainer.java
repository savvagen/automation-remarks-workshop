package com.example.java.models.elements_pattern;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public abstract class ElementContainer {

    public SelenideElement self;


    public ElementContainer(String cssSelector) {
        this.self = $(cssSelector);
    }

    public ElementContainer(By selector) {
        this.self = $(selector);
    }

    public ElementContainer(SelenideElement self) {
        this.self = self;
    }

    public ElementContainer(){}

    public SelenideElement getSelf() {
        return self;
    }

    public void setSelf(SelenideElement self) {
        this.self = self;
    }
}
