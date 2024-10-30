package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeechDto {
    private Long id;
    private String slug;
    private String title;
    private String speaker;
    private String thumb;
    private AttachFile presentation;
    private String youtubeUrl;
}
