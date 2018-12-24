package com.example.java.models.elements_pattern.pages.MainPage;

import com.codeborne.selenide.Selenide;
import com.example.java.models.elements_pattern.pages.MainPage.elements.LoginModal;
import com.example.java.models.elements_pattern.pages.MainPage.elements.RegisterModal;
import com.example.java.models.elements_pattern.pages.WebPage;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;


public class MainPage extends WebPage {


    public MainPage(){
        this.url = "/index.html";
        this.title = "\n" + "        WeaveSocks\n" + "    ";
    }

    public LoginModal loginModal = new LoginModal(".modal-content");
    public RegisterModal registerModal = new RegisterModal(".modal-content");



    @Step
    public MainPage open(){
        return Selenide.open("/", MainPage.class);
    }

    @Step
    public LoginModal login(){
        header.loginButton.click();
        return this.loginModal;
    }

    @Step
    public RegisterModal register(){
        header.registerButton.click();
        return this.registerModal;
    }

    @Step
    public MainPage logout(){
        header.logoutButton.shouldBe(visible).scrollTo().click();
        header.loginButton.shouldBe(visible);
        return this;
    }

}
