package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NexageProjectPage extends Form {

    public NexageProjectPage() {
        super(By.xpath("//ol[@class='breadcrumb']//li[contains(text(), 'Nexage')]"), "Nexage project page");
    }
}
