package kg.biom.justice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "page_view", schema = "just")
public class PageViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4184040171846215896L;

    @Id
    @Column(name = "page_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> content;
}
