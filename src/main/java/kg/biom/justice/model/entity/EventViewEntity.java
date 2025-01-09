package kg.biom.justice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kg.biom.justice.model.dto.AttachFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event_view", schema = "just")
public class EventViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3600150103716225402L;

    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "event_lang_id")
    private Long eventLangId;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

    @Column(name = "content")
    private String content;

    @Column(name = "thumb")
    private String thumbnail;

    @Column(name = "agenda", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AttachFile> agenda;

    @Column(name = "press", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AttachFile> press;

    @Column(name = "pictures", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> pictures;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "active")
    private boolean active;
}
