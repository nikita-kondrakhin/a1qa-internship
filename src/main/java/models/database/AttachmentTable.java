package models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentTable extends BaseTable {
    private File content;
    private String contentType;
    private Integer testId;
}
