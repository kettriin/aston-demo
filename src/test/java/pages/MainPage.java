package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    ElementsCollection navigationPanelItem = $$("nav a, nav p");
    ElementsCollection footerLinks = $$("footer a, [class*='footer'] a, [class*='Footer'] a");
    private final SelenideElement
            logo = $("img[alt='Logo']"),
            footer = $("footer, [class*='footer']"),
            aboutButton = $(byText("О нас")),
            hideCoockieButton = $(byText("Подтверждаю")),
            navigationPanel = $("nav");

    @Step("Скрыть уведомление о cookies")
    public MainPage hideCoockie() {
        hideCoockieButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить наличие логотипа на главной странице")
    public boolean logoExists() {
        return logo.exists();
    }

    @Step("Проверить видимость навигационной панели")
    public MainPage navigationPanelIsVisiable() {
        navigationPanel.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что пункт '{navItem}' отображается в навигационной панели")
    public MainPage navItemsVisibleInHeader(String navItem) {
        navigationPanelItem.filterBy(text(navItem))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Проверить, что пункт '{naviItem}' содержит ссылку '{expectedLink}'")
    public MainPage navItemsHaveLinks(String naviItem, String expectedLink) {
        navigationPanelItem.findBy(text(naviItem))
                .shouldHave(attribute("href", expectedLink));
        return this;
    }

    @Step("Нажать на кнопку 'О нас' в навигационной панели")
    public AboutUsPage clickAboutUsButton() {
        aboutButton.click();
        return new AboutUsPage();
    }

    @Step("Проверить наличие футера на странице")
    public boolean isFooterPresent() {
        return footer.exists();
    }

    @Step("Получить количество ссылок в футере")
    public int footerLinksCounter() {
        return footerLinks.size();
    }
}