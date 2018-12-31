package com.example.java.models.kiss_pattern.pages;

import com.codeborne.selenide.*;
import com.example.java.models.kiss_pattern.WebPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage extends WebPage {

    public CatalogPage(){
        this.url = "/category.html";
        this.title = "\n" + "        WeaveSocks\n" + "    ";
    }


    public ElementsCollection filters = $$("#filters input");
    public ElementsCollection products = $$("#products .product");
    public ElementsCollection productNumbers = $$("#products-number a");
    public SelenideElement applyButton = $("#filters-form > a");
    public SelenideElement clearButton = $("a[onclick='resetTags()']");
    public SelenideElement sortingSelect = $(byName("sort-by"));


    public CatalogPage open(){
        return Selenide.open(this.url, CatalogPage.class);
    }

    public CatalogPage sortBy(String sortBy){
        sortingSelect.shouldBe(visible).selectOption(sortBy);
        return this;
    }

    public CatalogPage filterBy(String filter){
        filters.filterBy(value(filter)).first().click();
        applyButton.click();
        return this;
    }

    public CatalogPage showProducts(Integer number){
        productNumbers.filterBy(attribute("onclick", "setNewPageSize(" + number.toString() + ")")).first().click();
        productNumbers.first().shouldBe(visible);
        return this;
    }

}
