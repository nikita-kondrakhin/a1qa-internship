package models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogTableRecord extends BaseTableRecord {
    String content;
    Integer isException;
    Integer testId;
}
