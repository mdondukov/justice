package kg.biom.justice.repository;

import kg.biom.justice.model.entity.PageViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PageViewRepository extends JpaRepository<PageViewEntity, Long> {
    @Query("SELECT p FROM PageViewEntity p WHERE p.slug = :slug AND p.lang = :lang")
    Optional<PageViewEntity> findBySlug(@Param("slug") String slug, @Param("lang") String lang);
}
