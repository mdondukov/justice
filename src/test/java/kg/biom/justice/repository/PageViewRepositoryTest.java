package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageViewRepositoryTest {

    @Autowired
    PageViewRepository pageViewRepository;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findBySlug() {
        assertDoesNotThrow(() -> pageViewRepository.findBySlug("about", defaultLocale))
                .ifPresent(System.out::println);
    }
}