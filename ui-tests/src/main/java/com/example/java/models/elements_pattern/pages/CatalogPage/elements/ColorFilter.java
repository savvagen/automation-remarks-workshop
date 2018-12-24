package com.example.java.models.elements_pattern.pages.CatalogPage.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;

public class ColorFilter extends ElementContainer {

    public ColorFilter(SelenideElement self) {
        super(self);
    }

    public ElementsCollection colorCheckBoxes = this.self.$$("input[type='checkbox']");


}
