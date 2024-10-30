package kg.biom.justice.service;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.EventDto;

import java.util.List;

/**
 * Сервис для работы с событиями
 */
public interface EventService {
    List<EventDto> getEvents(int count, AppContext context);
}
