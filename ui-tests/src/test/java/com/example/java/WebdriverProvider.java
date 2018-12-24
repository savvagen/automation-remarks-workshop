package com.example.java;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.openqa.selenium.Platform.*;

public class WebdriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities dc) {
        dc.setBrowserName("chrome");
        dc.setVersion("70.0");
        dc.setCapability("enableVNC", true);
        dc.setCapability("enableVideo", true);
        dc.setCapability("screenResolution", "1960x1280x24");
        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        dc.setCapability("videoName", "selenoid_recording.mp4");
        dc.setCapability("videoScreenSize", "1960x1280");
        dc.setPlatform(Platform.LINUX);
        dc.setJavascriptEnabled(true);
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    URI.create("http://selenoid:4444/wd/hub").toURL(), // http://209.97.181.33:4444/wd/hub
                    dc
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
