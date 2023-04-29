package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewProjectPage extends Form {
    private static final String NEW_PROJECT_NAME_DYNAMIC_LOCATOR = "//a[text()='%s']";

    public NewProjectPage(String projectName) {
        super(By.xpath(String.format(NEW_PROJECT_NAME_DYNAMIC_LOCATOR, projectName)), String.format("%s project page", projectName));
    }

    public boolean isProjectCreated(String projectName) {
        return getElementFactory().getLabel(By.xpath(String.format(NEW_PROJECT_NAME_DYNAMIC_LOCATOR, projectName)),"project label").state().isExist();
    }
}
