package com.example.java.models.elements_pattern;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public abstract class ElementsContainer {

    public ElementsCollection self;


    public ElementsContainer(String cssSelector) {
        this.self = $$(cssSelector);
    }

    public ElementsContainer(By selector) {
        this.self = $$(selector);
    }

    public ElementsContainer(ElementsCollection elmentList) {
        this.self = elmentList;
    }

    public ElementsCollection getSelf() {
        return self;
    }

    public void setSelf(ElementsCollection self) {
        this.self = self;
    }

}
