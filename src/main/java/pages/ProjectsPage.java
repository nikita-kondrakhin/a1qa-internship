package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {
    private final ILabel versionLabel = getElementFactory().getLabel(By.xpath("//p[contains(@class,'footer-text')]//*[contains(text(), 'Version:')]"), "Version");
    private final IButton nexageButton = getElementFactory().getButton(By.xpath("//a[contains(text(), 'Nexage')]"), "Nexage button");

    public ProjectsPage() {
        super(By.xpath("//div[@class='panel-heading' and contains(text(), 'Available projects')]"), "Projects page");
    }

    public String getVersionLabel() {
        versionLabel.state().isDisplayed();
        return versionLabel.getText();
    }

    public void clickNexageButton() {
        nexageButton.click();
    }
}
