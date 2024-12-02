package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.DocumentType;
import kg.biom.justice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/docs")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    private final MessageSource messageSource;

    @Value("${content.documents.limit.page}")
    private int pageLimit;

    @GetMapping
    public String getDocumentsPage(@RequestParam(defaultValue = "0") int page, Model model, Locale locale) {
        Page<DocumentDto> documentsPage = documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, page, pageLimit, locale);
        model.addAttribute("docsPage", documentsPage);
        model.addAttribute("currentPage", page);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.constructor", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.constructor.documents", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/docs";
    }
}
