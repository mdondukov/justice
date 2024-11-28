package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import kg.biom.justice.model.dto.SpeechDto;
import kg.biom.justice.service.SpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/speeches")
@RequiredArgsConstructor
public class SpeechController {
    private final SpeechService speechService;
    private final MessageSource messageSource;

    @Value("${content.speeches.limit.page}")
    private int pageLimit;

    @GetMapping
    public String getSpeechesPage(@RequestParam(defaultValue = "0") int page, Model model, Locale locale) {
        Page<SpeechDto> speechesPage = speechService.getSpeeches(page, pageLimit, locale);
        model.addAttribute("speechesPage", speechesPage);
        model.addAttribute("currentPage", page);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.education", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.education.speeches", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/speeches";
    }

    @GetMapping("/{slug}")
    public String getSpeechPage(@PathVariable String slug, Model model, Locale locale) {
        SpeechDto speech = speechService.getSpeech(slug, locale);
        model.addAttribute("speech", speech);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.education", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.education.speeches", null, locale), "/speeches"),
                new BreadcrumbDto(speech.getTitle(), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/speech";
    }
}
