package com.example.groovy

import com.example.java.models.User
import io.qameta.allure.Step

import static com.codeborne.selenide.Condition.visible
import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.$

class LoginModal {

    def emailField = $("#username-modal")
    def passwordField = $("#password-modal")
    def submitButton = $("button[onclick='return login()']")
    def loginMessage = $("div#login-message div")

    @Step
    def loginWith(User user){
        emailField.shouldBe(visible).setValue(user.username)
        passwordField.setValue(user.password); submitButton.click()
        new MainPage()
    }


}
