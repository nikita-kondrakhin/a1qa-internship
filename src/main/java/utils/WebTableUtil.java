package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.WebTableColumnNames;
import lombok.experimental.UtilityClass;
import models.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class WebTableUtil {
    private static final Logger logger = AqualityServices.getLogger();
    private static final String TH_TD = "th,td";
    private static final String TR = "tr";

 /*   public static ArrayList<Test> getTestsFromWebTable(String body) {
        logger.info("Getting tests from HTML table");
        Document doc = Jsoup.parse(body, StandardCharsets.UTF_8.name());
        Elements rows = doc.select(TR);
        Elements first = rows.first().select(TH_TD);
        List<String> headers = new ArrayList<>();
        for (Element header : first) {
            headers.add(header.text());
        }
        ArrayList<Test> testsList = new ArrayList<>();
        Map<String, String> tuple = new HashMap<>();
        for (int row = 1; row < rows.size(); row++) {
            Elements colVals = rows.get(row).select(TH_TD);
            int colCount = 0;
            for (Element colVal : colVals) {
                tuple.put(headers.get(colCount++), colVal.text());
            }
            Test test = getTestsFromMap(tuple);
            if (!test.getLatestTestResult().equals("In progress")) {
                testsList.add(test);
            }
            tuple.clear();
        }
        return testsList;
    }*/

    public static List<Test> getTestsFromWebTable(String webTableInnerHtml) {
        logger.info("Getting tests from web table");
        Document doc = Jsoup.parse(webTableInnerHtml, StandardCharsets.UTF_8.name());
        Elements rows = doc.select(TR);
        Elements firstRow = rows.first().select(TH_TD);
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
        test.setTestName(tuple.get(WebTableColumnNames.NAME_COLUMN.getColumnName()));
        test.setTestMethod(tuple.get(WebTableColumnNames.METHOD_COLUMN.getColumnName()));
        test.setLatestTestResult(tuple.get(WebTableColumnNames.LATEST_TEST_RESULT_COLUMN.getColumnName()));
        test.setLatestTestStartTime(tuple.get(WebTableColumnNames.START_TIME_COLUMN.getColumnName()));
        test.setLatestTestEndTime(tuple.get(WebTableColumnNames.END_TIME_COLUMN.getColumnName()));
        test.setLatestTestDuration(tuple.get(WebTableColumnNames.DURATION_COLUMN.getColumnName()));
        return test;
    }

    public static List<String> getTestNamesFromWebTable(String body) {
        logger.info("Getting test names from web table");
        Document doc = Jsoup.parse(body, StandardCharsets.UTF_8.name());
        Elements rows = doc.select(TR);
        Elements firstRow = rows.first().select(TH_TD);
        List<String> headers = new ArrayList<>();
        for (Element header : firstRow) {
            headers.add(header.text());
        }
        ArrayList<String> testNamesList = new ArrayList<>();
        Map<String, String> tuple = new HashMap<>();
        for (int row = 1; row < rows.size(); row++) {
            Elements columnValues = rows.get(row).select(TH_TD);
            tuple.put(headers.get(0), columnValues.get(0).text());
            String testName = tuple.get(WebTableColumnNames.NAME_COLUMN.getColumnName());
            if (testName != null && !testName.isEmpty()) {
                testNamesList.add(testName);
            }
            tuple.clear();
        }
        return testNamesList;
    }
}
