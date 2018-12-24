package com.example.groovy

import com.codeborne.selenide.WebDriverRunner

import static com.codeborne.selenide.WebDriverRunner.*


abstract class WebPage {

    def url
    def title

    def shouldBeOpened = {
        assert getWebDriver().currentUrl.contains(url)
        assert getWebDriver().title == title
    }


}