package kg.biom.justice.service.impl;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.EventDto;
import kg.biom.justice.model.entity.EventEntity;
import kg.biom.justice.repository.EventRep;
import kg.biom.justice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRep eventRep;

    @Value("${content.base.path}")
    private String basePath;

    @Value("${content.date.pattern}")
    private String datePattern;

    @Override
    public List<EventDto> getEvents(int limit, AppContext context) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "publish_date"));
        return eventRep.findAll(context.locale(), pageable).getContent().stream()
                .map(this::convertToDto)
                .toList();
    }

    private EventDto convertToDto(EventEntity event) {
        EventDto dto = new EventDto();
        dto.setId(dto.getId());
        dto.setTitle(event.getTitle());
        dto.setDescr(event.getDescr());
        dto.setContent(event.getContent());
        dto.setThumb(String.format("%s%s", basePath, event.getThumbnail()));
        dto.setPublishDate(ContentUtil.toDtString(event.getPublishDate(), datePattern));
        return dto;
    }
}
