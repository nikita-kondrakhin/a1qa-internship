package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewProjectPage extends Form {
    private static final String NEW_PROJECT_NAME_DYNAMIC_LOCATOR = "//a[text()='%s']";

    public NewProjectPage() {
        super(By.xpath("//div[@class='panel-heading' and contains(text(),'Total tests progress')]"), "New project page");
    }

    public boolean isTestNameVisible(String projectName) {
        return getElementFactory().getLabel(By.xpath(String.format(NEW_PROJECT_NAME_DYNAMIC_LOCATOR, projectName)),"project label").state().isDisplayed();
    }
}
