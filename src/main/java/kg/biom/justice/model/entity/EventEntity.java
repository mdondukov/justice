package kg.biom.justice.model.entity;

import jakarta.persistence.*;
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
@Table(name = "event", schema = "just")
public class EventEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 454105276125453859L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

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

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventLangEntity> translations;
}
