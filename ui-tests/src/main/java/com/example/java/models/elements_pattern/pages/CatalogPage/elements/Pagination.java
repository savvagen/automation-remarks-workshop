package com.example.java.models.elements_pattern.pages.CatalogPage.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;

import static com.codeborne.selenide.Selenide.*;

public class Pagination extends ElementContainer {

    public Pagination(SelenideElement self) {
        super(self);
    }

    public ElementsCollection paginationButtons = $$("#products-number > a");
    public SelenideElement sortDropDown = $("select.form-control");
    public SelenideElement paginationTitle = $("#totalProducts");

}
