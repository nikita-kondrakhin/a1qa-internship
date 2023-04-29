package models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentTableRecord extends BaseTableRecord {
    private byte[] content;
    private String contentType;
    private Integer testId;
}
