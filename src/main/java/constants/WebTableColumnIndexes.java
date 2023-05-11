package constants;

public enum WebTableColumnIndexes {
    NAME_COLUMN(0),
    METHOD_COLUMN(1),
    LATEST_TEST_RESULT_COLUMN(2),
    START_TIME_COLUMN(3),
    END_TIME_COLUMN(4),
    DURATION_COLUMN(5);

    private final int columnIndex;

    WebTableColumnIndexes(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
