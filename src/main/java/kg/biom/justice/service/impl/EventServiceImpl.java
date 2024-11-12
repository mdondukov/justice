package kg.biom.justice.service.impl;

import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.EventDto;
import kg.biom.justice.model.entity.EventViewEntity;
import kg.biom.justice.repository.EventViewRepository;
import kg.biom.justice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventViewRepository eventViewRepository;

    @Value("${content.base.path}")
    private String basePath;

    @Value("${content.date.pattern}")
    private String datePattern;

    @Override
    public Page<EventDto> getEvents(int page, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "publishDate"));
        return eventViewRepository.findAllByLang(locale.getLanguage(), pageable).map(this::convertToDto);
    }

    private EventDto convertToDto(EventViewEntity entity) {
        EventDto event = new EventDto();
        event.setId(entity.getId());
        event.setSlug(entity.getSlug());
        event.setTitle(entity.getTitle());
        event.setDescr(entity.getDescr());
        event.setContent(entity.getContent());
        event.setThumb(ContentUtil.mergePath(basePath, entity.getThumbnail()));
        event.setYoutubeUrl(entity.getYoutubeUrl());
        event.setPublishDate(ContentUtil.toDtString(entity.getPublishDate(), datePattern));

        if (entity.getAgenda() != null)
            event.setAgenda(entity.getAgenda().stream().map(file -> ContentUtil.handleAttachFile(file, basePath)).toList());

        if (entity.getPress() != null)
            event.setPress(entity.getPress().stream().map(file -> ContentUtil.handleAttachFile(file, basePath)).toList());

        if (entity.getPictures() != null)
            event.setPictures(entity.getPictures().stream().map(p -> ContentUtil.mergePath(basePath, p)).toList());

        return event;
    }


}
