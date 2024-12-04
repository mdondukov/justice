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
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutController {
    private final MessageSource messageSource;

    @GetMapping
    public String about(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/about";
    }

    @GetMapping("/partners")
    public String partners(Model model, Locale locale) {
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
