package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogTable extends BaseTable{
    String content;
    Integer isException;
    Integer testId;
}
