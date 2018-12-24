package com.example.java.models.kiss_pattern.modals;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginModal {

    public SelenideElement usernameField = $("#username-modal");
    public SelenideElement passwordField = $("#password-modal");
    public SelenideElement loginButton = $("button[onclick='return login()']");
    public SelenideElement title = $("h4.modal-title");
    public SelenideElement closeButton = $("button.close");
    public SelenideElement message = $("#login-message > div");


    public MainPage loginAs(User user){
        usernameField.shouldBe(visible).setValue(user.username);
        passwordField.setValue(user.password);
        loginButton.click();
        return new MainPage();
    }

    public MainPage close(){
        closeButton.click();
        return new MainPage();
    }


}
