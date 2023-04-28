package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.TestsWebTableColumnNames;
import lombok.experimental.UtilityClass;
import models.webapp.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.*;

@UtilityClass
public class WebTableUtil {
    private static final Logger logger = AqualityServices.getLogger();
    private static final String TH_TD = "th,td";
    private static final String TR = "tr";

    public static List<Test> getTestsFromWebTable(String html) {
        logger.info("Getting tests from web table");
        Document doc = Jsoup.parse(html, StandardCharsets.UTF_8.name());
        Elements rows = doc.select(TR);
        Elements firstRow = Objects.requireNonNull(rows.first()).select(TH_TD);
        List<String> headers = new ArrayList<>();
        for (Element header : firstRow) {
            headers.add(header.text());
        }
        ArrayList<Test> testsList = new ArrayList<>();
        Map<String, String> tuple = new HashMap<>();
        for (int row = 1; row < rows.size(); row++) {
            Elements columnValues = rows.get(row).select(TH_TD);
            int columnCount = 0;
            for (Element columnValue : columnValues) {
                tuple.put(headers.get(columnCount++), columnValue.text());
            }
            testsList.add(getTestsFromMap(tuple));
            tuple.clear();
        }
        return testsList;
    }

    private static Test getTestsFromMap(Map<String, String> tuple) {
        Test test = new Test();
        test.setTestName(tuple.get(TestsWebTableColumnNames.NAME_COLUMN.getColumnName()));
        test.setTestMethod(tuple.get(TestsWebTableColumnNames.METHOD_COLUMN.getColumnName()));
        test.setLatestTestResult(tuple.get(TestsWebTableColumnNames.LATEST_TEST_RESULT_COLUMN.getColumnName()));
        test.setLatestTestStartTime(tuple.get(TestsWebTableColumnNames.START_TIME_COLUMN.getColumnName()));
        test.setLatestTestEndTime(tuple.get(TestsWebTableColumnNames.END_TIME_COLUMN.getColumnName()));
        test.setLatestTestDuration(tuple.get(TestsWebTableColumnNames.DURATION_COLUMN.getColumnName()));
        return test;
    }

    public static List<String> getTestNamesFromWebTable(String body) {
        logger.info("Getting test names from web table");
        Document doc = Jsoup.parse(body, StandardCharsets.UTF_8.name());
        Elements rows = doc.select(TR);
        Elements firstRow = Objects.requireNonNull(rows.first()).select(TH_TD);
        List<String> headers = new ArrayList<>();
        for (Element header : firstRow) {
            headers.add(header.text());
        }
        ArrayList<String> testNamesList = new ArrayList<>();
        Map<String, String> tuple = new HashMap<>();
        for (int row = 1; row < rows.size(); row++) {
            Elements columnValues = rows.get(row).select(TH_TD);
            tuple.put(headers.get(0), columnValues.get(0).text());
            String testName = tuple.get(TestsWebTableColumnNames.NAME_COLUMN.getColumnName());
            if (testName != null && !testName.isEmpty()) {
                testNamesList.add(testName);
            }
            tuple.clear();
        }
        return testNamesList;
    }
}
