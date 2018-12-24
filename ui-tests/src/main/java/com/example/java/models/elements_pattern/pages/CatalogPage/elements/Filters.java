package com.example.java.models.elements_pattern.pages.CatalogPage.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import com.example.java.models.elements_pattern.options.Colors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Filters extends ElementContainer {

    public Filters(String cssSelector) {
        super(cssSelector);
    }

    public SelenideElement cleanButton = $("a[onclick='resetTags()']");
    public SelenideElement applyButton = $("a[onclick^='setNewTags']");
    public ColorFilter colorFilter = new ColorFilter($("#filters"));

    public CatalogPage filterBy(Colors.Color color){
        colorFilter.colorCheckBoxes.findBy(value(color.name)).click();
        applyButton.scrollIntoView(true).click();
        return new CatalogPage();
    }

}
