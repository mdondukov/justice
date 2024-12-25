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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getEventsPage(@RequestParam(defaultValue = "0") int page, Model model, Locale locale) {
        Page<EventDto> eventsPage = eventService.getEvents(page, pageLimit, locale);
        model.addAttribute("eventsPage", eventsPage);
        model.addAttribute("currentPage", page);

        List<BreadcrumbDto> breadcrumbs = List.of(
                new BreadcrumbDto(messageSource.getMessage("section.about", null, locale), "/about")
        );
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "pages/events";
    }

    @GetMapping("/{slug}")
    public String getEventPage(@PathVariable String slug, Model model, Locale locale) {
        EventDto event = eventService.getEvent(slug, locale);
        model.addAttribute("event", event);

        List<EventDto> latestEvents = eventService.getLatestEvents(event.getId(), pageLimit, locale);
        model.addAttribute("latestEvents", latestEvents);

        return "pages/event";
    }
}
