package com.example.java.models.kiss_pattern.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.WebPage;
import com.example.java.models.kiss_pattern.modals.LoginModal;
import com.example.java.models.kiss_pattern.modals.RegisterModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class MainPage extends WebPage {

    public MainPage(){
        this.url = "/";
    }

    public SelenideElement loginButton = $("#login");
    public SelenideElement registerButton = $("#register");
    public SelenideElement accountButton = $("#howdy > a");
    public SelenideElement modal = $(".modal-content");
    public SelenideElement logoutButton = $(byText("Logout"));

    public LoginModal loginModal = new LoginModal();
    public RegisterModal registerModal = new RegisterModal();



    @Step
    public MainPage open(){
        return Selenide.open(this.url, this.getClass());
    }

    @Step
    public LoginModal login(){
        loginButton.click();
        loginModal.title.shouldBe(visible).shouldHave(text("Customer Login"));
        return loginModal;
    }

    @Step
    public RegisterModal register(){
        registerButton.click();
        return registerModal;
    }

    @Step
    public MainPage logOut(){
        logoutButton.shouldBe(visible).click();
        loginButton.shouldBe(visible);
        return this;
    }




}
