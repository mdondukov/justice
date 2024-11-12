package kg.biom.justice.controller;

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

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EventService eventService;
    private final SpeechService speechService;
    private final PosterService posterService;
    private final DocumentService documentService;

    @Value("${content.home.limit.default}")
    private int defaultLimit;

    @Value("${content.home.limit.documents}")
    private int documentsLimit;

    @GetMapping("/")
    public String home(Model model, Locale locale) {
        model.addAttribute("events", eventService.getEvents(0, defaultLimit, locale).getContent());
        model.addAttribute("speeches", speechService.getSpeeches(0, defaultLimit, locale).getContent());
        documentService.getDocument(1L, locale).ifPresent(manual -> model.addAttribute("manual", manual));
        model.addAttribute("documents", documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, documentsLimit, locale));
        model.addAttribute("analytics", documentService.getDocuments(DocumentType.ANALYTICS, defaultLimit, locale));
        model.addAttribute("posters", posterService.getPosters(0, defaultLimit, locale).getContent());
        return "pages/home";
    }
}
