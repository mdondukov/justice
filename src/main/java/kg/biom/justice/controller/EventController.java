package kg.biom.justice.controller;

import kg.biom.justice.model.dto.BreadcrumbDto;
import kg.biom.justice.model.dto.EventDto;
import kg.biom.justice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final MessageSource messageSource;

    @Value("${content.events.limit.page}")
    private int pageLimit;

    @GetMapping
    public String getAllEventsPage(@RequestParam(defaultValue = "0") int page, Model model, Locale locale) {
        Page<EventDto> eventsPage = eventService.getEvents(page, pageLimit, locale);
        model.addAttribute("eventsPage", eventsPage);
        model.addAttribute("currentPage", page);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), null),
                new BreadcrumbDto(messageSource.getMessage("section.about.events", null, locale), null)
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/events";
    }
}
