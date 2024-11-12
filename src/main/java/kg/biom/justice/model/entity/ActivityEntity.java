package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "activity", schema = "just")
public class ActivityEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1048495270157196477L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slug")
    private String slug;

    @ManyToMany(mappedBy = "activities")
    private List<DocumentEntity> documents;

    @ManyToMany(mappedBy = "activities")
    private List<PosterEntity> posters;

    @ManyToMany(mappedBy = "activities")
    private List<SpeechEntity> speeches;

    @Column(name = "ord")
    private int ord;
}
