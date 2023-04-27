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

//    @Override //todo
//    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        }
//        if (obj == null || obj.getClass() != this.getClass()) {
//            return false;
//        }
//        Test test = (Test) obj;
//
//        return testName.equals(test.testName) &&
//                testMethod.equals(test.testMethod) &&
//                latestTestResult.equalsIgnoreCase(test.latestTestResult) &&
//                latestTestStartTime.equals(test.latestTestStartTime) &&
//                (latestTestEndTime != null && !latestTestEndTime.equals("")) ?
//                latestTestEndTime.equals(test.latestTestEndTime) : test.latestTestEndTime == null &&
//                latestTestDuration.equals(test.latestTestDuration);
//    }
}
