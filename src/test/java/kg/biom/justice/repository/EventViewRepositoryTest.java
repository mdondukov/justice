package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventViewRepositoryTest {

    @Autowired
    private EventViewRepository eventViewRepository;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "publishDate"));
        var result = assertDoesNotThrow(() ->
                eventViewRepository.findAllActiveByLang(defaultLocale, pageable));

        for (var entity : result) System.out.println(entity);
    }
}