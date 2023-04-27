package pages;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.Test;
import org.openqa.selenium.By;
import utils.ParseTableUtil;

import java.util.ArrayList;
import java.util.List;

public class NexageProjectPage extends Form {
    private final ITextBox webTable = getElementFactory().getTextBox(By.xpath("//table[@class='table']/.."), "Tests table");
    private static final String INNER_HTML = "innerHTML";

    public NexageProjectPage() {
        super(By.xpath("//ol[@class='breadcrumb']//li[contains(text(), 'Nexage')]"), "Nexage project page");
    }

    public boolean isWebTableDisplayed() {
        webTable.state().waitForDisplayed();
        return webTable.state().isDisplayed();
    }

    public List<Test> getTestsFromNexageProjectPage() {
        return ParseTableUtil.getTestsFromWebTable(webTable.getElement().getAttribute(INNER_HTML));
    }

    public List<String> getTestNamesFromNexageProjectPage() {
        return ParseTableUtil.getTestNamesFromWebTable(webTable.getElement().getAttribute(INNER_HTML));
    }
}
