package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.CookieData;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import pages.ProjectsPage;

public class UiSteps {
    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final Browser browser = AqualityServices.getBrowser();


    public static void verifyProjectsPageIsOpen() {
        Assert.assertTrue(projectsPage.state().isDisplayed(), String.format("%s is not open", projectsPage.getName()));
    }

    public static void sendCookieWithToken(String token) {
        Cookie cookie = new Cookie(CookieData.COOKIE_PARAMETER, token);
        browser.getDriver().manage().addCookie(cookie);
    }

    public static void refreshPage() {
        browser.refresh();
    }
}
