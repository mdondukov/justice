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
@RequestMapping("/constructor")
@RequiredArgsConstructor
public class ConstructorController {
    private final MessageSource messageSource;

    @GetMapping("/consult")
    public String consult(Model model, Locale locale) {
        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.constructor", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.constructor.consult", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/consult";
    }
}
