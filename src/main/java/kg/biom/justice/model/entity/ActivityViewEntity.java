package kg.biom.justice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "activity_view", schema = "just")
public class ActivityViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2688001114000851756L;

    @Id
    @Column(name = "activity_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "lang")
    private String lang;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "descr")
    private String descr;

    @Column(name = "ord")
    private Integer ord;
}
