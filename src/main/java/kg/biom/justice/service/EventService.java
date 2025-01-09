package kg.biom.justice.service;

import kg.biom.justice.model.dto.EventDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;

/**
 * Сервис для работы с событиями
 */
public interface EventService {

    Page<EventDto> getActiveEvents(int page, int limit, Locale locale);

    List<EventDto> getLatestEvents(Long currentEventId, int limit, Locale locale);

    EventDto getActiveEvent(String slug, Locale locale);

    Page<EventDto> getEvents(int page, int limit, String searchQuery, String lang);

    EventDto getEvent(Long id, String lang);

    EventDto getEmptyEvent(String lang);
}
