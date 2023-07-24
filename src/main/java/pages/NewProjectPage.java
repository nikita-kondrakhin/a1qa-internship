package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewProjectPage extends Form {
    private static final String NEW_TEST_NAME_DYNAMIC_LOCATOR = "//a[text()='%s']";

    public NewProjectPage() {
        super(By.xpath("//div[@class='panel-heading' and contains(text(),'Total tests progress')]"), "New project page");
    }

    public boolean isTestNameVisible(String testName) {
        return getElementFactory().getLabel(By.xpath(String.format(NEW_TEST_NAME_DYNAMIC_LOCATOR, testName)),"Test name").state().isDisplayed();
    }

    public void clickNewTest(String testName) {
        getElementFactory().getLabel(By.xpath(String.format(NEW_TEST_NAME_DYNAMIC_LOCATOR, testName)),"Test element").click();
    }
}
