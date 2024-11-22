package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "document_activity", schema = "just")
public class DocumentActivityEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6197896158953567186L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "activity_id")
    private Long activityId;
}

