package kg.biom.justice.repository;

import kg.biom.justice.model.entity.ActivityViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ActivityViewRepository extends JpaRepository<ActivityViewEntity, Long> {

    List<ActivityViewEntity> findByLangOrderByOrd(String lang);
}
