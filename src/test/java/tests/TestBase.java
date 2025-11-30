package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }

    public static void defaultConfig() {
        Configuration.remote = System.getProperty("farm_link");
        Configuration.browser = System.getProperty("browser", "chrome");
        //Configuration.browserVersion = System.getProperty("version", "128.0");
        Configuration.browserSize = System.getProperty("resolution", "1920x1080");
        Configuration.baseUrl = "https://astondevs.ru/";
        Configuration.pageLoadStrategy = "eager";
    }

    public static void defaultCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    public static void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
