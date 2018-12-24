package com.example.java.models.elements_pattern.pages.CatalogPage;

import com.codeborne.selenide.Selenide;
import com.example.java.models.elements_pattern.pages.CatalogPage.elements.Filters;
import com.example.java.models.elements_pattern.pages.WebPage;
import com.example.java.models.elements_pattern.pages.CatalogPage.elements.ProductList;

import static com.codeborne.selenide.Selenide.*;

public class CatalogPage extends WebPage {

    public CatalogPage(){
        this.url = "/category.html";
        this.title = "\n" + "        WeaveSocks\n" + "    ";
    }

    public ProductList products = new ProductList($$("#products .product"));
    public Filters filters = new Filters("#filters-form");

    public CatalogPage open(){
        return Selenide.open("/category.html", CatalogPage.class);
    }

}
