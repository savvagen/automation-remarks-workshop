package com.example.groovy

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.example.java.models.User
import io.qameta.allure.Step
import sun.applet.Main

import static com.codeborne.selenide.Condition.*
import static com.codeborne.selenide.Selenide.*

class MainPage extends WebPage {


    MainPage(){
        this.url = "/"
        this.title = "WeaveSocks"
    }



    def loginButton = $("#login > a")
    def registerButton = $("#register > a")
    def userNameButton = $("#howdy")
    def loginModal = new LoginModal()


    @Step
    def open(){
        open("/", this.class)
    }

    @Step
    def login() {
        loginButton.click()
        loginModal
    }

    @Step
    def loginAs(User user){
        loginButton.click()
        return loginModal.loginWith(user)
    }


}
