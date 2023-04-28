package models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTable extends BaseTable{
    private String name;
    private Integer statusId;
    private String methodName;
    private Integer projectId;
    private Integer sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private Integer authorId;
}
