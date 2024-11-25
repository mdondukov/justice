package kg.biom.justice.repository;

import kg.biom.justice.model.entity.SpeechViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface SpeechViewRepository extends JpaRepository<SpeechViewEntity, Long> {

    @Query("SELECT s FROM SpeechViewEntity s WHERE s.lang = :lang AND s.active = true")
    Page<SpeechViewEntity> findAllByLang(@Param("lang") String lang, Pageable pageable);

    @Query("SELECT s FROM SpeechViewEntity s WHERE s.slug = :slug AND s.lang = :lang AND s.active = true")
    Optional<SpeechViewEntity> findBySlug(@Param("slug") String slug, @Param("lang") String lang);
}
