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
@Table(name = "speech", schema = "just")
public class SpeechEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4651568736316604152L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "thumb")
    private String thumbnail;

    @Column(name = "presentation", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private AttachFile presentation;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToMany(mappedBy = "speech", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpeechLangEntity> translations;

    @ManyToMany
    @JoinTable(
            name = "speech_activity",
            schema = "just",
            joinColumns = @JoinColumn(name = "speech_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<ActivityEntity> activities;
}
