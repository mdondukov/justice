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
@Table(name = "poster_view", schema = "just")
public class PosterViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3514937256320150546L;

    @Id
    @Column(name = "poster_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

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
}
