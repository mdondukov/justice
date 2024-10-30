package kg.biom.justice.service.impl;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.ActivityCode;
import kg.biom.justice.model.enums.DocumentType;
import kg.biom.justice.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public List<DocumentDto> getDocuments(DocumentType type, int limit, AppContext context) {
        return getDocuments().stream()
                .filter(document -> document.getType().equals(type))
                .limit(limit)
                .toList();
    }

    @Override
    public Optional<DocumentDto> getDocument(Long id, AppContext context) {
        return getDocuments().stream()
                .filter(document -> document.getId().equals(id))
                .findFirst();
    }

    private List<DocumentDto> getDocuments() {
        return List.of(
                new DocumentDto(1L,
                        "Руководство по общественному мониторингу",
                        "Публикация “Руководство по общественному мониторингу Целей Устойчивого Развития и социально-экологической поддержке населения” посвящена описанию процесса проведения мониторинга с участием граждан в сборе данных, а также механизмов взаимодействия по проведению мониторинга между гражданами, органами государственной власти и местного самоуправления.",
                        DocumentType.MANUAL,
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "file-pdf",
                        1),
                new DocumentDto(2L,
                        "Аналитический отчет о механизмах защиты персональных данных и неприкосновенности частной жизни в КР",
                        "",
                        DocumentType.ANALYTICS,
                        List.of(ActivityCode.PRIVACY),
                        Collections.emptyList(),
                        "file-pdf",
                        1),
                new DocumentDto(3L,
                        "Аналитический отчет о механизмах обеспечения экологических прав в КР",
                        "",
                        DocumentType.ANALYTICS,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        2),
                new DocumentDto(4L,
                        "Аналитический отчет о механизмах защиты и продвижения гендерного равенства в КР",
                        "",
                        DocumentType.ANALYTICS,
                        List.of(ActivityCode.GENDER),
                        Collections.emptyList(),
                        "file-pdf",
                        3),
                new DocumentDto(5L,
                        "Анализ законодательства  Кыргызской Республики в области обращения химических веществ и отходов",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        1),
                new DocumentDto(6L,
                        "Биоразнообразие и изменение климата. Проект решения, представленный Председателем Рабочей группы II",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        2),
                new DocumentDto(7L,
                        "Выполнение Кыргызстаном обязательств, вытекающих из Конвенции по биоразнообразию",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-word",
                        3),
                new DocumentDto(8L,
                        "Каталог проектных идей по “Зеленой экономике” Кыргызстана",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        4),
                new DocumentDto(9L,
                        "Международные экологические конвенции, стороной которых является Кыргызская Республика",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-word",
                        5),
                new DocumentDto(10L,
                        "Отчет о ходе достижения Целей Устойчивого Развития в Кыргызской Республике",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        6),
                new DocumentDto(11L,
                        "Обращение НПО стран Центральной Азии по вопросам изменения климата",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-word",
                        7),
                new DocumentDto(12L,
                        "Международные обязательства Кыргызской Республики в области охраны окружающей среды",
                        "",
                        DocumentType.LEGAL_DOCUMENT,
                        List.of(ActivityCode.ECOLOGY),
                        Collections.emptyList(),
                        "file-pdf",
                        8)
        );
    }
}
