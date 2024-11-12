package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeechDto {
    private Long id;
    private String slug;
    private String title;
    private String descr;
    private String speaker;
    private String thumb;
    private AttachFile presentation;
    private String youtubeUrl;
    private String publishDate;
    private List<String> activityCodes;
}
