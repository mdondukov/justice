package kg.biom.justice.service;

import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.DocumentType;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface DocumentService {
    List<DocumentDto> getDocuments(DocumentType type, int limit, Locale locale);

    List<DocumentDto> getDocuments(DocumentType type, Long activityId, int limit, Locale locale);

    Optional<DocumentDto> getDocument(Long id, Locale locale);
}
