package kg.biom.justice.repository;

import kg.biom.justice.model.entity.SpeechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpeechRepository extends JpaRepository<SpeechEntity, Long> {

    @Query("SELECT a.slug FROM SpeechEntity s JOIN s.activities a WHERE s.id = :speechId")
    List<String> findActivitySlugsBySpeechId(@Param("speechId") Long speechId);
}
