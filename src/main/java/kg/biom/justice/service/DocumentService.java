package kg.biom.justice.service;

import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.DocumentType;
import org.springframework.data.domain.Page;

import java.util.Locale;
import java.util.Optional;

public interface DocumentService {
    Page<DocumentDto> getDocuments(DocumentType type, int page, int limit, Locale locale);

    Page<DocumentDto> getDocuments(DocumentType type, Long activityId, int page, int limit, Locale locale);

    Optional<DocumentDto> getDocument(Long id, Locale locale);
}
