package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import kg.biom.justice.model.dto.PageDto;
import kg.biom.justice.service.DocumentService;
import kg.biom.justice.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutController {
    private final MessageSource messageSource;
    private final PageService pageService;
    private final DocumentService documentService;

    @GetMapping
    public String about(Model model, Locale locale) {
        PageDto page = pageService.getPage("about", locale);
        model.addAttribute("data", page.getContent());

        documentService.getDocument(70L, locale)
                .flatMap(document -> document.getFiles().stream()
                        .filter(file -> file.getLang().equals(locale.getLanguage()))
                        .findFirst())
                .ifPresent(document -> model.addAttribute("booklet", document));

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/about";
    }

    @GetMapping("/partners")
    public String partners(Model model, Locale locale) {
        PageDto page = pageService.getPage("partners", locale);
        model.addAttribute("data", page.getContent());

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), "/about"),
                new BreadcrumbDto(messageSource.getMessage("section.about.partners", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/partners";
    }

    @GetMapping("/monitoring")
    public String monitoring(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), "/about"),
                new BreadcrumbDto(messageSource.getMessage("section.about.monitoring", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/monitoring";
    }
}
