package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import kg.biom.justice.model.dto.AttachFile;
import kg.biom.justice.model.enums.DocumentType;
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
@Table(name = "document", schema = "just")
public class DocumentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3307443592165253905L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dtype")
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(name = "files", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AttachFile> files;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "ord")
    private int ord;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentLangEntity> translations;

    @ManyToMany
    @JoinTable(
            name = "document_activity",
            schema = "just",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<ActivityEntity> activities;
}
