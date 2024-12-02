package kg.biom.justice.controller;

import kg.biom.justice.model.dto.DocumentDto;
import kg.biom.justice.model.enums.ActivityCode;
import kg.biom.justice.model.enums.DocumentType;
import kg.biom.justice.service.DocumentService;
import kg.biom.justice.service.EventService;
import kg.biom.justice.service.PosterService;
import kg.biom.justice.service.SpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final EventService eventService;
    private final SpeechService speechService;
    private final PosterService posterService;
    private final DocumentService documentService;

    @Value("${content.home.limit.default}")
    private int defaultLimit;

    @Value("${content.documents.limit.home}")
    private int documentsLimit;

    @GetMapping("/")
    public String home(Model model, Locale locale) {
        model.addAttribute("events", eventService.getEvents(0, defaultLimit, locale).getContent());
        model.addAttribute("speeches", speechService.getSpeeches(0, defaultLimit, locale).getContent());
        documentService.getDocument(1L, locale).ifPresent(manual -> model.addAttribute("manual", manual));

        Map<String, List<DocumentDto>> documents = new HashMap<>();

        documents.put(ActivityCode.ECOLOGY.name(),
                documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, 1L, 0, documentsLimit, locale).getContent());

        documents.put(ActivityCode.GENDER.name(),
                documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, 2L, 0, documentsLimit, locale).getContent());

        documents.put(ActivityCode.PRIVACY.name(),
                documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, 3L, 0, documentsLimit, locale).getContent());

        model.addAttribute("documents", documents);

        model.addAttribute("analytics", documentService.getDocuments(DocumentType.ANALYTICS, 0, defaultLimit, locale).getContent());
        model.addAttribute("posters", posterService.getPosters(0, defaultLimit, locale).getContent());
        return "pages/home";
    }
}
