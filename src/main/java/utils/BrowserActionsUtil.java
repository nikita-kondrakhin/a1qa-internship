package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;

@UtilityClass
public class BrowserActionsUtil {
    private static final Logger logger = AqualityServices.getLogger();
    private final Browser browser = AqualityServices.getBrowser();
    private final RemoteWebDriver driver = browser.getDriver();

    public static void addCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public static void goBack() {
        browser.goBack();
    }

    public static void refresh() {
        browser.refresh();
    }

    public static void switchToIframe(String id) {
        logger.info("Switching to iframe");
        driver.switchTo().frame(id);
    }

    public static void switchToDefaultContent() {
        logger.info("Switching to default content");
        driver.switchTo().defaultContent();
    }

    public static void executeJavaScript(String script) {
        logger.info(String.format("Executing JavaScript '%s' method", script));
        browser.executeScript(script);
    }

    public static byte[] getScreenshot() {
        logger.info("Taking webpage screenshot");
        return browser.getScreenshot();
    }
}
