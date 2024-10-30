package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import kg.biom.justice.model.dto.AttachFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "event", schema = "just")
public class EventEntity extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 454105276125453859L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

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
