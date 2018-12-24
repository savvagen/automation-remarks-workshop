package com.example.java.tests.examples;

import com.example.java.BaseTest;
import com.example.java.models.elements_pattern.pages.CatalogPage.CatalogPage;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.example.java.models.elements_pattern.options.Colors.*;

@Tag("Example")
public class NavigationPannelTests extends BaseTest {

    @RegisterExtension
    public static MainPage mainPage = new MainPage();
    @RegisterExtension
    public static CatalogPage catalogPage = new CatalogPage();



    @Test
    void shouldOpenLoginModal(){
        mainPage.open().login()
                .getSelf().shouldBe(visible);
        mainPage.loginModal.title.shouldHave(text("Customer Login"));
    }

    @Test
    void shouldOpenRegisterModal(){
        mainPage.open().register()
                .getSelf().shouldBe(visible);
        mainPage.registerModal.title.shouldHave(text("Register"));
    }

    @Test
    void shouldNavigateToCatalog(){
        mainPage.open().header.catalogDropDown.click();
        catalogPage.shouldBeOpened();
    }

    @Test
    void shouldNavigateToCatalogFromNavigationButtons(){
        mainPage.open().header.catalogDropDown.hover();
        mainPage.header.navigateTo(Color.BROWN)
                .shouldBeOpened()
                .shouldHaveUrl(baseUrl + catalogPage.url + "?tags=brown");
        catalogPage.products.getSelf().shouldHaveSize(3);
        catalogPage.filters.colorFilter.colorCheckBoxes.find(value("brown")).shouldBe(selected);
    }



}
