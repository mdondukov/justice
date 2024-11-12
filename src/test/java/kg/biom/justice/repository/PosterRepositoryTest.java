package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PosterRepositoryTest {

    @Autowired
    private PosterRepository posterRepository;

    @Test
    void findActivitySlugsByPosterId() {
        Long posterId = 3L;
        var result = assertDoesNotThrow(() ->
                posterRepository.findActivitySlugsByPosterId(posterId));

        result.forEach(System.out::println);
    }
}