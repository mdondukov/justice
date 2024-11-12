package kg.biom.justice.model.dto;

import kg.biom.justice.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private Long id;
    private String title;
    private String descr;
    private DocumentType type;
    private List<String> activityCodes;
    private List<AttachFile> files;
    private String icon;
    private int ord;
}
