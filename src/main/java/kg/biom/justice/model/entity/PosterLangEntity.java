package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "poster_lang", schema = "just")
public class PosterLangEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3198433851553557532L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_id", nullable = false)
    private PosterEntity poster;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;
}
