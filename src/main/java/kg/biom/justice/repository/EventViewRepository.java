package kg.biom.justice.repository;

import kg.biom.justice.model.entity.EventViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EventViewRepository extends JpaRepository<EventViewEntity, Long> {

    @Query("SELECT e FROM EventViewEntity e WHERE e.lang = :lang AND e.active = true")
    Page<EventViewEntity> findAllByLang(@Param("lang") String lang, Pageable pageable);
}
