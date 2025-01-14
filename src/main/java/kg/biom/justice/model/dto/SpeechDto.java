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
    private String descr;
    private PersonDto person;
    private String thumb;
    private AttachFile presentation;
    private String youtubeUrl;
    private String publishDate;
}
