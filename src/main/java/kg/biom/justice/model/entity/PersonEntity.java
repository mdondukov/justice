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
@Table(name = "person", schema = "just")
public class PersonEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1053162412890751742L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "photo")
    private String photo;
}
