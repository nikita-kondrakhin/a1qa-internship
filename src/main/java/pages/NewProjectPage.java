package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewProjectPage extends Form {
    protected NewProjectPage(By locator, String name) {
        super(locator, name);
    }
}
