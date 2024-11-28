package kg.biom.justice.repository;

import kg.biom.justice.model.entity.PosterViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PosterViewRepository extends JpaRepository<PosterViewEntity, Long> {

    @Query("SELECT p FROM PosterViewEntity p WHERE p.lang = :lang AND p.active = true")
    Page<PosterViewEntity> findAllByLang(@Param("lang") String lang, Pageable pageable);

    @Query("SELECT p FROM PosterViewEntity p WHERE p.slug = :slug AND p.lang = :lang AND p.active = true")
    Optional<PosterViewEntity> findBySlug(@Param("slug") String slug, @Param("lang") String lang);
}
