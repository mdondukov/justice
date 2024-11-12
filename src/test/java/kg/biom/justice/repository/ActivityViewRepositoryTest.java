package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityViewRepositoryTest {

    @Autowired
    private ActivityViewRepository activityViewRepository;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findByLangOrderByOrd() {
        var result = assertDoesNotThrow(() ->
                activityViewRepository.findByLangOrderByOrd(defaultLocale));

        result.forEach(System.out::println);
    }
}