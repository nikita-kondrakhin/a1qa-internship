package pages;

import aquality.selenium.core.visualization.ImageFunctions;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.awt.*;

public class NewTestPage extends Form {
    public NewTestPage() {
        super(By.xpath("//div[contains(text(),'Common info')]"), "Created test page");
    }

    public Image getScreenshot() {
        return ImageFunctions.getScreenshotAsImage(getScreenshotElement().getElement());
    }

    public void openScreenshot() {
        getScreenshotElement().click();
    }

    private ILink getScreenshotElement() {
        return getElementFactory().getLink(By.xpath("//a[contains(@href,'image')]"), "Screenshot thumbnail");
    }
}
