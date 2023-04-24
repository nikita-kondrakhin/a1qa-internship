package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private String testName;
    private String testMethod;
    private String latestTestResult;
    private String latestTestStartTime;
    private String latestTestEndTime;
    private String latestTestDuration;
}
