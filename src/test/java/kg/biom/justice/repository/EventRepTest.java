package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventRepTest {

    @Autowired
    private EventRep eventRep;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void findAll() {
        var pageable = PageRequest.of(0, 3);
        var result = assertDoesNotThrow(() -> eventRep.findAll(defaultLocale, pageable));
        for (var entity : result) System.out.println(entity);
    }
}