package com.example.java.tests.examples;

import com.example.java.BaseTest;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.codeborne.selenide.Condition.*;
import static com.example.java.models.elements_pattern.options.Colors.Color.*;

@Tag("Example")
public class CatalogTests extends BaseTest {

    @RegisterExtension
    public CatalogPage catalogPage = new CatalogPage();


    @Test
    void shouldCheckCatalogPage(){
        catalogPage.open().products
                .getSelf().filterBy(visible).shouldHaveSize(6);
    }

    @Test
    void shouldFilterByColor(){
        catalogPage.open()
                .filters.filterBy(BROWN);
        catalogPage.products.self.shouldHaveSize(3);
    }


}
