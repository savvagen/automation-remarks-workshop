package com.example.java.models.elements_pattern.pages.CatalogPage.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import io.qameta.allure.Step;

public class Product extends ElementContainer {

    public Product(SelenideElement self) {
        super(self);
    }

    public SelenideElement title = this.self.$(".text h3 a"),
                            price = this.self.$(".price"),
                            cartButton = this.self.$("a[onclick^='addToCart']");

    @Step
    public CatalogPage addToCart(){
        cartButton.click();
        return new CatalogPage();
    }

}
