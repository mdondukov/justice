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
@Table(name = "person_view", schema = "just")
public class PersonViewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2546678696023344592L;

    @Id
    @Column(name = "person_id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "lang")
    private String lang;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "org")
    private String org;

    @Column(name = "descr")
    private String descr;

    @Column(name = "photo")
    private String photo;
}
