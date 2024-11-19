package kg.biom.justice.model.dto;

import kg.biom.justice.model.enums.AttachFileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachFile implements Serializable {
    private AttachFileType type;
    private String path;
    private String lang;
}
