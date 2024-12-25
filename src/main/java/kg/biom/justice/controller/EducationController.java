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
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final MessageSource messageSource;

    @GetMapping("/manuals")
    public String about(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.education", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/manuals";
    }
}
