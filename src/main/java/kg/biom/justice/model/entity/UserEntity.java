package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import kg.biom.justice.model.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "just")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -9163284869348700838L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "attempts", nullable = false)
    private int failedAttempts;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "firstname", length = 100)
    private String firstName;

    @Column(name = "lastname", length = 100)
    private String lastName;
}
