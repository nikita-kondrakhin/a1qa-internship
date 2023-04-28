package constants;

public enum TestsWebTableColumnNames {
    NAME_COLUMN("Test name"),
    METHOD_COLUMN("Test method"),
    LATEST_TEST_RESULT_COLUMN("Latest test result"),
    START_TIME_COLUMN("Latest test start time"),
    END_TIME_COLUMN("Latest test end time"),
    DURATION_COLUMN("Latest test duration (H:m:s.S)");

    private final String columnName;

    TestsWebTableColumnNames(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
