package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosterDto {
    private Long id;
    private String slug;
    private String title;
    private String descr;
    private String thumb;
    private List<AttachFile> files;
    private List<String> activityCodes;
}
