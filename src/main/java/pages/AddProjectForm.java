package pages;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {
    private final ITextBox projectName = getElementFactory().getTextBox(By.id("projectName"), "Project name textbox");

    public AddProjectForm() {
        super(By.xpath("//h4[@id='title']"), "Add project form");
    }
}
