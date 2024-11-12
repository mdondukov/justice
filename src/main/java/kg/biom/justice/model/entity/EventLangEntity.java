package kg.biom.justice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event_lang", schema = "just")
public class EventLangEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2500136256739804150L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

    @Column(name = "content")
    private String content;

    @Column(name = "youtube_url")
    private String youtubeUrl;
}
