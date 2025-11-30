package pages;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.webdriver;

public class AboutUsPage {

    @Step("Текущий url соответствует странице About Us")
    public boolean isOnAboutUsPage() {
        return webdriver().driver().url().contains("about-us");
    }
}
