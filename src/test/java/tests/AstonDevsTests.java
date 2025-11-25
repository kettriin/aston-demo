package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты на блоки и содержание главной страницы")
@Tag("MAIN_PAGE_CONTENT")
public class AstonDevsTests {
    MainPage mainPage = new MainPage();

    @BeforeAll
    static void beforeAnyMainPageTest() {
        Configuration.remote = System.getProperty("farm_link");
        Configuration.browser = System.getProperty("browser", "chrome");
        //Configuration.browserVersion = System.getProperty("version", "128.0");
        Configuration.browserSize = System.getProperty("resolution", "1920x1080");
        Configuration.baseUrl = "https://astondevs.ru/";
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEachTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open("/");
        mainPage.hideCoockie();
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }

    @DisplayName("Лого присутствует на главной")
    @Test
    void logoIsDisplayedOnTheMainPage() {
        assertTrue(mainPage.logoExists());
    }

    @ValueSource(strings = {
            "Доменная экспертиза", "Услуги для бизнеса", "Проекты",
            "Карьера", "ASTON медиа", "О нас"
    })
    @ParameterizedTest(name = "В шапке отображается информационный раздел {0}")
    void navItemsVisibleInHeader(String navItem){
        mainPage.navigationPanelIsVisiable();
        mainPage.navItemsVisibleInHeader(navItem);
    }

    @CsvSource({
            "Проекты, https://astondevs.ru/our-projects",
            "О нас, https://astondevs.ru/about-us"
    })
    @ParameterizedTest(name = "В шапке информационный раздел {0} содержит ссылку {1}")
    void navItemsLinks(String navItem, String expectedLink){
        mainPage.navItemsHaveLinks(navItem, expectedLink);
    }

    @DisplayName("Переход на страницу \"О нас\"")
    @Test
    public void contactButtonRedirect() {
        mainPage.clickAboutButton();
        String currentUrl = webdriver().driver().url();
        assertTrue(currentUrl.contains("about-us"));
    }
}
