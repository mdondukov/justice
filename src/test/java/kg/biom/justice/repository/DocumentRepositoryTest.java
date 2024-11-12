package kg.biom.justice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void findActivitySlugsByDocumentId() {
        Long documentId = 3L;
        var result = assertDoesNotThrow(() ->
                documentRepository.findActivitySlugsByDocumentId(documentId));

        result.forEach(System.out::println);
    }
}