package models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTableRecord extends BaseTableRecord {
    String content;
    Integer isException;
    Integer testId;
}
