package kg.biom.justice.repository;

import kg.biom.justice.model.entity.PosterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PosterRepository extends JpaRepository<PosterEntity, Long> {

    @Query("SELECT a.slug FROM PosterEntity p JOIN p.activities a WHERE p.id = :posterId")
    List<String> findActivitySlugsByPosterId(@Param("posterId") Long posterId);
}
