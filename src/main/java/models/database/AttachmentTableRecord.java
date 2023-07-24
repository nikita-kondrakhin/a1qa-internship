package models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentTableRecord extends BaseTableRecord {
    private byte[] content;
    private String contentType;
    private Integer testId;
}
