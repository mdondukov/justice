package kg.biom.justice.repository;

import kg.biom.justice.model.enums.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentViewRepositoryTest {

    @Autowired
    private DocumentViewRepository documentViewRepository;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findById() {
        Long id = 1L;
        var result = assertDoesNotThrow(() ->
                documentViewRepository.findByIdAndLang(id, defaultLocale));

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());

        result.ifPresent(System.out::println);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "ord"));
        var result = assertDoesNotThrow(() ->
                documentViewRepository.findAllByTypeAndLang(DocumentType.ANALYTICS, defaultLocale, pageable));

        result.getContent().forEach(System.out::println);
    }
}