package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String slug;
    private String title;
    private String descr;
    private String content;
    private String thumb;
    private List<AttachFile> agenda;
    private List<AttachFile> press;
    private List<String> pictures;
    private String youtubeUrl;
    private String publishDate;
}
