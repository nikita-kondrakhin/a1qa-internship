package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.CookieData;
import constants.TestData;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import pages.NexageProjectPage;
import pages.ProjectsPage;
import utils.ExtractNumberUtil;

public class UiSteps {
    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final NexageProjectPage nexageProjectPage = new NexageProjectPage();
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

    public static void verifyVariant() {
        int actualVariantNumber = ExtractNumberUtil.getVariantNumber(projectsPage.getVersionLabel());
        Assert.assertEquals(actualVariantNumber, TestData.VARIANT_NUMBER, "Variant number is not correct");
    }

    public static void goToNexageProjectPage() {
        projectsPage.clickNexageButton();
//        AqualityServices.getConditionalWait().waitFor(() -> nexageProjectPage.state().isDisplayed()); //todo
        Assert.assertTrue(nexageProjectPage.state().isDisplayed(), String.format("%s is not open", nexageProjectPage.getName()));
    }

    public static void goBackToProjectsPage() {
        browser.goBack();
    }

    public static void addNewProject() {
        projectsPage.clickAddButton();
    }
}
