package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.AboutUsPage;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты на блоки и содержание главной страницы")
@Tag("MAIN_PAGE_CONTENT")
public class AstonDevsTests extends TestBase {

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
    void navItemsVisibleInHeader(String navItem) {
        mainPage.navigationPanelIsVisiable();
        mainPage.navItemsVisibleInHeader(navItem);
    }

    @CsvSource({
            "Проекты, https://astondevs.ru/our-projects",
            "О нас, https://astondevs.ru/about-us"
    })
    @ParameterizedTest(name = "В шапке информационный раздел {0} содержит ссылку {1}")
    void navItemsLinks(String navItem, String expectedLink) {
        mainPage.navItemsHaveLinks(navItem, expectedLink);
    }

    @DisplayName("Переход на страницу \"О нас\"")
    @Test
    public void contactButtonRedirect() {
        AboutUsPage aboutUsPage = mainPage.clickAboutUsButton();
        sleep(5000);
        assertTrue(aboutUsPage.isOnAboutUsPage());
    }

    @DisplayName("Футер присутствует на странице")
    @Test
    public void mainPageWithFooter() {
        assertTrue(mainPage.isFooterPresent());
    }

    @DisplayName("Футтер содержит ссылки")
    @Test
    public void smokeTest_FooterLinks() {
        assertTrue(mainPage.footerLinksCounter() >= 1);
    }
}
