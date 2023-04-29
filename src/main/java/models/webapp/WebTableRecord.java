package models.webapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebTableRecord {
    private String testName;
    private String testMethod;
    private String latestTestResult;
    private String latestTestStartTime;
    private String latestTestEndTime;
    private String latestTestDuration;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        WebTableRecord test = (WebTableRecord) object;
        return testName.equals(test.testName) &&
                testMethod.equals(test.testMethod) &&
                latestTestResult.equalsIgnoreCase(test.latestTestResult) &&
                latestTestStartTime.equals(test.latestTestStartTime) &&
                (latestTestEndTime != null && !latestTestEndTime.equals("")) ?
                latestTestEndTime.equals(test.latestTestEndTime) : test.latestTestEndTime == null &&
                latestTestDuration.equals(test.latestTestDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTestName(), getTestMethod(), getLatestTestResult(),
                getLatestTestStartTime(), getLatestTestEndTime(), getLatestTestDuration());
    }
}
