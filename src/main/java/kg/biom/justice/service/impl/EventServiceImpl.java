package kg.biom.justice.service.impl;

import kg.biom.justice.exception.NotFoundException;
import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.AttachFile;
import kg.biom.justice.model.dto.EventDto;
import kg.biom.justice.model.entity.EventEntity;
import kg.biom.justice.model.entity.EventViewEntity;
import kg.biom.justice.repository.EventRepository;
import kg.biom.justice.repository.EventViewRepository;
import kg.biom.justice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventViewRepository eventViewRepository;
    private final EventRepository eventRepository;

    @Value("${content.base.url}")
    private String baseUrl;

    @Value("${content.date.zone}")
    private String dateZone;

    @Override
    public Page<EventDto> getActiveEvents(int page, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "publishDate"));
        return eventViewRepository.findAllActiveByLang(locale.getLanguage(), pageable)
                .map(this::convertToDto);
    }

    @Override
    public List<EventDto> getLatestEvents(Long currentEventId, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "publishDate"));
        return eventViewRepository.findAllActiveByLang(locale.getLanguage(), currentEventId, pageable)
                .map(this::convertToDto)
                .getContent();
    }

    @SneakyThrows
    @Override
    public EventDto getActiveEvent(String slug, Locale locale) {
        return eventViewRepository.findActiveBySlug(slug, locale.getLanguage())
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundException("Event not found with slug: " + slug));
    }

    @Override
    public Page<EventDto> getEvents(int page, int limit, String searchQuery, String lang) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "publishDate"));

        if (!searchQuery.isBlank())
            return eventViewRepository.search(searchQuery, lang, pageable)
                    .map(this::convertToDto);

        return eventViewRepository.findAllByLang(lang, pageable)
                .map(this::convertToDto);
    }

    @SneakyThrows
    @Override
    public EventDto getEvent(Long id, String lang) {
        List<EventViewEntity> entities = eventViewRepository.findAllById(id);
        if (entities.isEmpty()) throw new NotFoundException("Event not found by id: " + id);
        return entities.stream()
                .filter(entity -> entity.getLang().equalsIgnoreCase(lang))
                .findFirst()
                .map(this::convertToDto)
                .orElseGet(() -> getEventWithEmptyTranslate(id, lang));
    }

    @Override
    public EventDto getEmptyEvent(String lang) {
        return EventDto.builder()
                .lang(lang)
                .title("")
                .descr("")
                .content("")
                .thumb("")
                .youtubeUrl("")
                .createDate(LocalDateTime.now(ZoneId.of(dateZone)))
                .publishDate(LocalDate.now(ZoneId.of(dateZone)))
                .active(false)
                .build();
    }

    @SneakyThrows
    private EventDto getEventWithEmptyTranslate(Long id, String lang) {
        return eventRepository.findById(id)
                .map(entity -> convertToDto(entity, lang))
                .orElseThrow(() -> new NotFoundException("Event not found by id: " + id));
    }

    private EventDto convertToDto(EventEntity entity, String lang) {
        EventDto dto = EventDto.builder()
                .id(entity.getId())
                .slug(entity.getSlug())
                .lang(lang)
                .title("")
                .descr("")
                .content("")
                .thumb(ContentUtil.mergePath(baseUrl, entity.getThumbnail()))
                .createDate(entity.getCreateDate().atZone(ZoneId.of(dateZone)).toLocalDateTime())
                .publishDate(entity.getPublishDate().atZone(ZoneId.of(dateZone)).toLocalDate())
                .active(entity.isActive())
                .build();

        return handle(dto, entity.getAgenda(), entity.getPress(), entity.getPictures());
    }

    private EventDto convertToDto(EventViewEntity entity) {
        EventDto dto = EventDto.builder()
                .id(entity.getId())
                .slug(entity.getSlug())
                .translateId(entity.getEventLangId())
                .lang(entity.getLang())
                .title(entity.getTitle())
                .descr(entity.getDescr())
                .content(entity.getContent())
                .thumb(ContentUtil.mergePath(baseUrl, entity.getThumbnail()))
                .youtubeUrl(entity.getYoutubeUrl())
                .createDate(entity.getCreateDate().atZone(ZoneId.of(dateZone)).toLocalDateTime())
                .publishDate(entity.getPublishDate().atZone(ZoneId.of(dateZone)).toLocalDate())
                .active(entity.isActive())
                .build();

        return handle(dto, entity.getAgenda(), entity.getPress(), entity.getPictures());
    }

    private EventDto handle(EventDto dto, List<AttachFile> agenda, List<AttachFile> press, List<String> pictures) {
        if (agenda != null)
            dto.setAgenda(agenda.stream().map(file ->
                    ContentUtil.handleAttachFile(file, baseUrl)).toList());

        if (press != null)
            dto.setPress(press.stream().map(file ->
                    ContentUtil.handleAttachFile(file, baseUrl)).toList());

        if (pictures != null)
            dto.setPictures(pictures.stream().map(picture ->
                    ContentUtil.mergePath(baseUrl, picture)).toList());

        return dto;
    }
}
