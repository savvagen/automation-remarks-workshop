package com.example.java.models.kiss_pattern.modals;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.User;
import com.example.java.models.kiss_pattern.pages.MainPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;

public class RegisterModal {


    public SelenideElement usernameField = $("#register-username-modal");
    public SelenideElement firstNameField = $("#register-first-modal");
    public SelenideElement lastNameField = $("#register-last-modal");
    public SelenideElement emailField = $("#register-email-modal");
    public SelenideElement passwordField = $("#register-password-modal");
    public SelenideElement registerButton = $("button[onclick='return register()']");
    public SelenideElement title = $(".modal-header h4");
    public SelenideElement closeButton = $("button.close");
    public SelenideElement message = $("#registration-message > div");


    public MainPage registerWith(User user){
        usernameField.shouldBe(visible).setValue(user.username);
        firstNameField.val(user.firstName);
        lastNameField.val(user.lastName);
        emailField.setValue(user.email);
        passwordField.setValue(user.password);
        registerButton.click();
        return new MainPage();
    }

    public MainPage close(){
        closeButton.click();
        return new MainPage();
    }



}
