package com.example.java.models.elements_pattern.pages.CatalogPage.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.example.java.models.elements_pattern.ElementsContainer;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ProductList extends ElementsContainer {

    public ProductList(ElementsCollection elements) {
        super(elements);
    }

    public Product get(Integer index){
        return new Product(this.self.get(index));
    }

    public ProductList filterBy(Condition condition){
        return new ProductList(this.self.filterBy(condition));
    }

    public Product find(Condition condition){
        return new Product(this.self.find(condition));
    }

    public Product first(){
        return new Product(this.self.first());
    }

    public ProductList first(Integer range){
        return new ProductList(this.self.first(range));
    }


    public Pagination pagination = new Pagination($("div[class='box info-bar']"));



    @Step
    public CatalogPage sertBy(String sortBy){
        pagination.sortDropDown.selectOption(sortBy);
        return new CatalogPage();
    }


}
