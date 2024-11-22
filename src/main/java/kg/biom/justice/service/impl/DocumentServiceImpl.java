package kg.biom.justice.service.impl;

import kg.biom.justice.model.dto.AttachFile;
import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.entity.DocumentViewEntity;
import kg.biom.justice.model.enums.AttachFileType;
import kg.biom.justice.model.enums.DocumentType;
import kg.biom.justice.repository.DocumentRepository;
import kg.biom.justice.repository.DocumentViewRepository;
import kg.biom.justice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentViewRepository documentViewRepository;
    private final DocumentRepository documentRepository;

    @Value("${content.base.path}")
    private String basePath;

    @Override
    public List<DocumentDto> getDocuments(DocumentType type, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "ord"));
        return documentViewRepository.findAllByType(type, locale.getLanguage(), pageable).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public List<DocumentDto> getDocuments(DocumentType type, Long activityId, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "ord"));
        return documentViewRepository.findAllByTypeAndActivityId(type, activityId, locale.getLanguage(), pageable).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public Optional<DocumentDto> getDocument(Long id, Locale locale) {
        return documentViewRepository.findById(id, locale.getLanguage())
                .map(this::convertToDto);
    }

    private DocumentDto convertToDto(DocumentViewEntity entity) {
        DocumentDto document = new DocumentDto();
        document.setId(entity.getId());
        document.setTitle(entity.getTitle());
        document.setDescr(entity.getDescr());
        document.setType(entity.getType());

        List<String> activityCodes = documentRepository.findActivitySlugsByDocumentId(entity.getId());
        document.setActivityCodes(activityCodes);

        if (entity.getFiles() == null) return document;

        Map<AttachFileType, Long> typeCounts = new HashMap<>();
        List<AttachFile> files = entity.getFiles().stream()
                .peek(file -> {
                    file.setPath(String.format("%s/%s", basePath, file.getPath()));
                    typeCounts.merge(file.getType(), 1L, Long::sum);
                })
                .toList();
        document.setFiles(files);

        typeCounts.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .ifPresentOrElse(type -> document.setIcon(type.getIcon()),
                        () -> document.setIcon(AttachFileType.OTHER.getIcon()));

        return document;
    }
}
