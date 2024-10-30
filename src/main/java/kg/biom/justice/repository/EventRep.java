package kg.biom.justice.repository;

import kg.biom.justice.model.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRep extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM just.event(:lang) ORDER BY create_date DESC", nativeQuery = true)
    Page<EventEntity> findAll(@Param("lang") String lang, Pageable pageable);
}
