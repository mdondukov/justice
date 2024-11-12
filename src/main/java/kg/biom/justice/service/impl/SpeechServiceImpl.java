package kg.biom.justice.service.impl;

import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.AttachFile;
import kg.biom.justice.model.dto.SpeechDto;
import kg.biom.justice.model.entity.SpeechViewEntity;
import kg.biom.justice.repository.SpeechRepository;
import kg.biom.justice.repository.SpeechViewRepository;
import kg.biom.justice.service.SpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SpeechServiceImpl implements SpeechService {
    private final SpeechViewRepository speechViewRepository;
    private final SpeechRepository speechRepository;

    @Value("${content.base.path}")
    private String basePath;

    @Value("${content.date.pattern}")
    private String datePattern;

    @Override
    public Page<SpeechDto> getSpeeches(int page, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "publishDate"));
        return speechViewRepository.findAllByLang(locale.getLanguage(), pageable).map(this::convertToDto);
    }

    private SpeechDto convertToDto(SpeechViewEntity entity) {
        SpeechDto speech = new SpeechDto();
        speech.setId(entity.getId());
        speech.setSlug(entity.getSlug());
        speech.setTitle(entity.getTitle());
        speech.setDescr(entity.getDescr());
        speech.setSpeaker(entity.getSpeaker());
        speech.setThumb(ContentUtil.mergePath(basePath, entity.getThumbnail()));
        speech.setYoutubeUrl(entity.getYoutubeUrl());
        speech.setPublishDate(ContentUtil.toDtString(entity.getPublishDate(), datePattern));

        if (entity.getPresentation() != null) {
            AttachFile presentation = entity.getPresentation();
            presentation.setPath(ContentUtil.mergePath(basePath, presentation.getPath()));
            speech.setPresentation(presentation);
        }

        List<String> activityCodes = speechRepository.findActivitySlugsBySpeechId(speech.getId());
        speech.setActivityCodes(activityCodes);

        return speech;
    }
}
