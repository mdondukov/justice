package kg.biom.justice.repository;

import kg.biom.justice.model.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query("SELECT a.slug FROM DocumentEntity d JOIN d.activities a WHERE d.id = :documentId")
    List<String> findActivitySlugsByDocumentId(@Param("documentId") Long documentId);
}
