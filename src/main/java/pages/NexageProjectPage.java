package pages;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.webapp.Test;
import org.openqa.selenium.By;
import utils.WebTableUtil;

import java.util.List;

public class NexageProjectPage extends Form {
    private static final String INNER_HTML = "innerHTML";
    private final ITextBox webTable = getElementFactory().getTextBox(By.xpath("//table[@class='table']/.."), "Tests table");

    public NexageProjectPage() {
        super(By.xpath("//ol[@class='breadcrumb']//li[contains(text(), 'Nexage')]"), "Nexage project page");
    }

    public boolean isWebTableDisplayed() {
        webTable.state().waitForDisplayed();
        return webTable.state().isDisplayed();
    }

    public List<Test> getTestsFromNexageProjectPage() {
        return WebTableUtil.getTestsFromWebTable(webTable.getElement().getAttribute(INNER_HTML));
    }

    public List<String> getTestNamesFromNexageProjectPage() {
        return WebTableUtil.getTestNamesFromWebTable(webTable.getElement().getAttribute(INNER_HTML));
    }
}
