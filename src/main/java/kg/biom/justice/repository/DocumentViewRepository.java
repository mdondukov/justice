package kg.biom.justice.repository;

import kg.biom.justice.model.entity.DocumentViewEntity;
import kg.biom.justice.model.enums.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface DocumentViewRepository extends JpaRepository<DocumentViewEntity, Long> {

    @Query("SELECT d FROM DocumentViewEntity d WHERE d.id = :id AND d.lang = :lang AND d.active = true")
    Optional<DocumentViewEntity> findById(
            @Param("id") Long id,
            @Param("lang") String lang
    );

    @Query("SELECT d FROM DocumentViewEntity d WHERE d.lang = :lang AND d.type = :type AND d.active = true")
    Page<DocumentViewEntity> findAllByType(
            @Param("type") DocumentType type,
            @Param("lang") String lang,
            Pageable pageable
    );

    @Query("""
                SELECT d
                FROM DocumentViewEntity d
                WHERE d.id IN (
                    SELECT da.documentId
                    FROM DocumentActivityEntity da
                    WHERE da.activityId = :activityId
                )
                  AND d.lang = :lang
                  AND d.type = :type
                  AND d.active = true
            """)
    Page<DocumentViewEntity> findAllByTypeAndActivityId(
            @Param("type") DocumentType documentType,
            @Param("activityId") Long activityId,
            @Param("lang") String lang,
            Pageable pageable);
}
