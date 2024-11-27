package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person_lang", schema = "just")
public class PersonLangEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6248178519910129742L;

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

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
}
