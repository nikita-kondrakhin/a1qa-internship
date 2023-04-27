package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {
    private static final String NEW_PROJECT_NAME_DYNAMIC_LOCATOR = "//a[text()='%s']";
    private final ILabel versionLabel = getElementFactory().getLabel(By.xpath("//p[contains(@class,'footer-text')]//*[contains(text(), 'Version:')]"), "Version");
    private final IButton nexageButton = getElementFactory().getButton(By.xpath("//a[contains(text(), 'Nexage')]"), "Nexage button");
    private final IButton addButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'btn') and contains(text(), '+Add')]"), "Add button");

    public ProjectsPage() {
        super(By.xpath("//div[@class='panel-heading' and contains(text(), 'Available projects')]"), "Projects page");
    }

    public String getVersionLabel() {
//        versionLabel.state().isDisplayed(); //todo
        return versionLabel.getText();
    }

    public void clickNexageButton() {
        nexageButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public boolean isProjectAdded(String projectName) {
        return getElementFactory().getLabel(By.xpath(String.format(NEW_PROJECT_NAME_DYNAMIC_LOCATOR, projectName)), "New project name").state().isExist();
    }

    public void clickNewProject(String projectName) { //todo
        getElementFactory().getLabel(By.xpath(String.format(NEW_PROJECT_NAME_DYNAMIC_LOCATOR, projectName)), "New project element").click();
    }
}
