package helpers;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
