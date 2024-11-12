package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PosterViewRepositoryTest {

    @Autowired
    private PosterViewRepository posterViewRepository;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findAllByLang() {
        var pageable = PageRequest.of(0, 3);
        var result = assertDoesNotThrow(() ->
                posterViewRepository.findAllByLang(defaultLocale, pageable));

        for (var entity : result) System.out.println(entity);
    }
}