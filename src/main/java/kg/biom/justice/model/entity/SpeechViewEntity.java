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

@Data
@NoArgsConstructor
@Entity
@Table(name = "speech_view", schema = "just")
public class SpeechViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3313279011696728270L;

    @Id
    @Column(name = "speech_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonViewEntity person;

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
}
