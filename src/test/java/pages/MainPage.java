package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public MainPage hideCoockie() {
        hideCoockieButton.shouldBe(visible).click();

        return this;
    }

    public boolean logoExists() {
        return logo.exists();
    }

    public MainPage navigationPanelIsVisiable() {
        navigationPanel.shouldBe(visible);

        return this;
    }

    public MainPage navItemsVisibleInHeader(String navItem) {
        navigationPanelItem.filterBy(text(navItem))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    public MainPage navItemsHaveLinks(String naviItem, String expectedLink) {
        navigationPanelItem.findBy(text(naviItem))
                .shouldHave(attribute("href", expectedLink));

        return this;
    }

    public MainPage clickAboutButton() {
        aboutButton.click();

        return this;
    }

    public boolean isFooterPresent() {
        return footer.exists();
    }

    public int footerLinksCounter() {
        return footerLinks.size();
    }
}
