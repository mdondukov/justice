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
@Table(name = "document_view", schema = "just")
public class DocumentViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7098475120365231125L;

    @Id
    @Column(name = "document_id")
    private Long id;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

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
    private Integer ord;
}
