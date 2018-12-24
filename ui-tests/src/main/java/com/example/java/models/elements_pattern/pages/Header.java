package com.example.java.models.elements_pattern.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import com.example.java.models.elements_pattern.options.Colors;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class Header extends ElementContainer {

    public Header(String cssSelector) {
        super(cssSelector);
    }

    public SelenideElement registerButton = $("#register > a");
    public SelenideElement userNameButton = $("#howdy > a");
    public SelenideElement loginButton = $("#login > a");
    public SelenideElement logoutButton = $("*[onclick='logout()']");
    public SelenideElement basketButton = $("#basket-overview");
    public SelenideElement basketCount = $("#numItemsInCart");
    // Vavigation
    public SelenideElement homeButton = $("#tabIndex");
    public SelenideElement catalogDropDown = $("#tabCatalogue");
    public ElementsCollection catalogItems = catalogDropDown.$$(".col-sm-3 a");
    public SelenideElement accountButton = $("#tabAccount a");

    @Step
    public CatalogPage navigateTo(Colors.Color tag){
        catalogItems.find(Condition.text(tag.name)).click();
        return new CatalogPage();
    }



}
