package com.example.java.models.elements_pattern.pages.MainPage.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.java.models.elements_pattern.ElementContainer;
import com.example.java.models.User;
import com.example.java.models.elements_pattern.pages.MainPage.MainPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RegisterModal extends ElementContainer {

    public RegisterModal(String cssSelector) { setSelf($(cssSelector)); }
    public RegisterModal(By selector) { setSelf($(selector)); }
    public RegisterModal() {}

    public SelenideElement usernameField = $("#register-username-modal"),
                            title = $("h4.modal-title"),
                            firstNameField = $("#register-first-modal"),
                            lastNameField = $("#register-last-modal"),
                            emailField = $("#register-email-modal"),
                            passwordField = $("#register-password-modal"),
                            registerButton = $("button[onclick='return register()']"),
                            closeButton = $("*[data-dismiss='modal']");

    @Step
    public MainPage registerWith(User user){
        usernameField.setValue(user.getUsername());
        firstNameField.setValue(user.getFirstName());
        lastNameField.setValue(user.getLastName());
        emailField.setValue(user.getEmail());
        passwordField.setValue(user.getPassword());
        registerButton.click();
        return new MainPage();
    }

    @Step
    public MainPage dismiss(){
        closeButton.click();
        return new MainPage();
    }


}
