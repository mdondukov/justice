package kg.biom.justice.repository;

import kg.biom.justice.model.entity.EventViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface EventViewRepository extends JpaRepository<EventViewEntity, Long> {

    @Query("SELECT e FROM EventViewEntity e WHERE e.lang = :lang AND e.active = true")
    Page<EventViewEntity> findAllActiveByLang(@Param("lang") String lang, Pageable pageable);

    @Query("SELECT e FROM EventViewEntity e WHERE e.id <> :excludeId AND e.lang = :lang AND e.active = true")
    Page<EventViewEntity> findAllActiveByLang(@Param("lang") String lang, @Param("excludeId") Long excludeId, Pageable pageable);

    @Query("SELECT e FROM EventViewEntity e WHERE e.slug = :slug AND e.lang = :lang AND e.active = true")
    Optional<EventViewEntity> findActiveBySlug(@Param("slug") String slug, @Param("lang") String lang);

    @Query("SELECT e FROM EventViewEntity e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :search, '%')) AND e.lang = :lang")
    Page<EventViewEntity> search(@Param("search") String search, @Param("lang") String lang, Pageable pageable);

    Page<EventViewEntity> findAllByLang(String lang, Pageable pageable);

    List<EventViewEntity> findAllById(Long id);
}
