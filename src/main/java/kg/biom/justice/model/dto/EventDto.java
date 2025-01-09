package kg.biom.justice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;

    @NotBlank(message = "Slug cannot be blank")
    private String slug;

    private Long translateId;
    private String lang;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String descr;

    @NotBlank(message = "Thumbnail cannot be blank")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Thumbnail must be a valid URL"
    )
    private String thumb;

    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$|^$",
            message = "YouTube URL must be a valid URL"
    )
    private String youtubeUrl;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    private String content;

    private List<AttachFile> agenda;
    private List<AttachFile> press;
    private List<String> pictures;
    private boolean active;
}
