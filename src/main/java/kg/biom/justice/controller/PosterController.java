package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import kg.biom.justice.model.dto.PosterDto;
import kg.biom.justice.service.PosterService;
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
@RequestMapping("/posters")
@RequiredArgsConstructor
public class PosterController {
    private final PosterService posterService;
    private final MessageSource messageSource;

    @Value("${content.posters.limit.page}")
    private int pageLimit;

    @GetMapping
    public String getPostersPage(@RequestParam(defaultValue = "0") int page, Model model, Locale locale) {
        Page<PosterDto> postersPage = posterService.getPosters(page, pageLimit, locale);
        model.addAttribute("postersPage", postersPage);
        model.addAttribute("currentPage", page);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.education", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/posters";
    }

    @GetMapping("/{slug}")
    public String getPosterPage(@PathVariable String slug, Model model, Locale locale) {
        PosterDto poster = posterService.getPoster(slug, locale);
        model.addAttribute("poster", poster);
        return "pages/poster";
    }
}
