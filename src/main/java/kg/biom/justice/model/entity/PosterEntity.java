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
@Table(name = "poster", schema = "just")
public class PosterEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6117336661606695357L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "thumb")
    private String thumbnail;

    @Column(name = "files", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AttachFile> files;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "ord")
    private int ord;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosterLangEntity> translations;

    @ManyToMany
    @JoinTable(
            name = "poster_activity",
            joinColumns = @JoinColumn(name = "poster_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<ActivityEntity> activities;
}
