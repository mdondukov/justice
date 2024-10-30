package kg.biom.justice.controller;

import kg.biom.justice.model.AppContext;
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

    @Value("${locale.default}")
    private String defaultLocale;

    @Value("${user.default}")
    private String defaultUser;

    @Value("${content.items.count}")
    private int itemsCount;

    @GetMapping("/")
    public String home(Model model, Locale locale) {
        var context = new AppContext(defaultUser, defaultLocale);
        model.addAttribute("events", eventService.getEvents(itemsCount, context));
        model.addAttribute("speeches", speechService.getSpeeches(itemsCount, context));
        documentService.getDocument(1L, context).ifPresent(manual -> model.addAttribute("manual", manual));
        model.addAttribute("documents", documentService.getDocuments(DocumentType.LEGAL_DOCUMENT, 24, context));
        model.addAttribute("analytics", documentService.getDocuments(DocumentType.ANALYTICS, itemsCount, context));
        model.addAttribute("posters", posterService.getPosters(itemsCount, context));
        return "pages/home";
    }
}
