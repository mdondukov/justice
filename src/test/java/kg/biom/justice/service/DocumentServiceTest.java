package kg.biom.justice.service;

import kg.biom.justice.model.enums.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void getDocuments() {
        var locale = Locale.forLanguageTag(defaultLocale);
        var result = assertDoesNotThrow(() ->
                documentService.getDocuments(DocumentType.ANALYTICS, 3, locale));

        result.forEach(System.out::println);
    }

    @Test
    void getDocument() {
        var locale = Locale.forLanguageTag(defaultLocale);
        var result = assertDoesNotThrow(() ->
                documentService.getDocument(1L, locale));

        result.ifPresent(System.out::println);
    }
}