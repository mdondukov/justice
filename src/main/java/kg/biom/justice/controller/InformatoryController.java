package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/informatory")
@RequiredArgsConstructor
public class InformatoryController {
    private final MessageSource messageSource;

    @GetMapping("/publications")
    public String publications(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.informatory", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.informatory.publications", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/publications";
    }

    @GetMapping("/presentations")
    public String presentations(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.informatory", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.informatory.presentations", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/presentations";
    }

    @GetMapping("/links")
    public String links(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.informatory", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.informatory.links", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/links";
    }
}
