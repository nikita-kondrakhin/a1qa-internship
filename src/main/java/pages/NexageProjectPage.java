package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import constants.WebTableColumnIndexes;
import models.webapp.WebTableRecord;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NexageProjectPage extends Form {
    private static final int HEADERS_ROW_INDEX = 1;
    private final IElement webTable = getElementFactory().getLabel(By.className("table"), "Web table");

    public NexageProjectPage() {
        super(By.xpath("//ol[@class='breadcrumb']//li[contains(text(), 'Nexage')]"), "Nexage project page");
    }

    public boolean isWebTableDisplayed() {
        webTable.state().waitForDisplayed();
        return webTable.state().isDisplayed();
    }

    public List<WebTableRecord> getTestsFromWebTable() {
        return IntStream.range(HEADERS_ROW_INDEX, getWebTableRows().size())
                .mapToObj(this::getTestRecordFromWebTable)
                .collect(Collectors.toList());
    }

    public List<String> getTestNamesFromWebTable() {
        return getWebTableRows().stream()
                .skip(HEADERS_ROW_INDEX)
                .map(row -> (IElement) row.findChildElement(By.tagName("td"), ElementType.LABEL))
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    private WebTableRecord getTestRecordFromWebTable(int rowIndex) {
        List<IElement> webTableColumns = getWebTableRows().get(rowIndex).findChildElements(By.tagName("td"), ElementType.LABEL);
        return WebTableRecord.builder()
                .testName(webTableColumns.get(WebTableColumnIndexes.NAME_COLUMN.getColumnIndex()).getText())
                .testMethod(webTableColumns.get(WebTableColumnIndexes.METHOD_COLUMN.getColumnIndex()).getText())
                .latestTestResult(webTableColumns.get(WebTableColumnIndexes.LATEST_TEST_RESULT_COLUMN.getColumnIndex()).getText())
                .latestTestStartTime(webTableColumns.get(WebTableColumnIndexes.START_TIME_COLUMN.getColumnIndex()).getText())
                .latestTestEndTime(webTableColumns.get(WebTableColumnIndexes.END_TIME_COLUMN.getColumnIndex()).getText())
                .latestTestDuration(webTableColumns.get(WebTableColumnIndexes.DURATION_COLUMN.getColumnIndex()).getText())
                .build();
    }

    private List<IElement> getWebTableRows() {
        return webTable.findChildElements(By.tagName("tr"), ElementType.LABEL);
    }
}
