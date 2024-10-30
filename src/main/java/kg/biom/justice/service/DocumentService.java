package kg.biom.justice.service;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    List<DocumentDto> getDocuments(DocumentType type, int limit, AppContext context);

    Optional<DocumentDto> getDocument(Long id, AppContext context);
}
