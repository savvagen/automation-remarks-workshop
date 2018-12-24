package com.example.java.models.elements_pattern.pages.MainPage.elements;


import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import com.example.java.models.User;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;

import static com.codeborne.selenide.Selenide.*;

public class LoginModal extends ElementContainer {

    public LoginModal(String cssSelector) { super(cssSelector); }
    public LoginModal(){}


    public SelenideElement title = $("h4.modal-title"),
            closeButton = $("*[data-dismiss='modal']"),
            usernameField = $("#username-modal"),
            passwordField = $("#password-modal"),
            loginButton = $("button[onclick='return login()']"),
            successMessage = $(byText("Login successful."));



    @Step
    public MainPage loginWith(User user){
        usernameField.setValue(user.getUsername());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        return new MainPage();
    }

    @Step
    public MainPage dismiss(){
        closeButton.click();
        return new MainPage();
    }






}
