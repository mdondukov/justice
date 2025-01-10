package kg.biom.justice.service.impl;

import kg.biom.justice.exception.NotFoundException;
import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.PosterDto;
import kg.biom.justice.model.entity.PosterViewEntity;
import kg.biom.justice.repository.PosterRepository;
import kg.biom.justice.repository.PosterViewRepository;
import kg.biom.justice.service.PosterService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public class PosterServiceImpl implements PosterService {
    private final PosterViewRepository posterViewRepository;
    private final PosterRepository posterRepository;

    @Value("${content.base.url}")
    private String baseUrl;

    @Override
    public Page<PosterDto> getPosters(int page, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createDate"));
        return posterViewRepository.findAllByLang(locale.getLanguage(), pageable).map(this::convertToDto);
    }

    @SneakyThrows
    @Override
    public PosterDto getPoster(String slug, Locale locale) {
        return posterViewRepository.findBySlug(slug, locale.getLanguage())
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundException("Poster not found with slug: " + slug));
    }

    private PosterDto convertToDto(PosterViewEntity entity) {
        PosterDto poster = new PosterDto();
        poster.setId(entity.getId());
        poster.setSlug(entity.getSlug());
        poster.setTitle(entity.getTitle());
        poster.setDescr(entity.getDescr());
        poster.setThumb(ContentUtil.mergePath(baseUrl, entity.getThumbnail()));

        if (entity.getFiles() != null)
            poster.setFiles(entity.getFiles().stream().map(f -> ContentUtil.handleAttachFile(f, baseUrl)).toList());

        List<String> activityCodes = posterRepository.findActivitySlugsByPosterId(poster.getId());
        poster.setActivityCodes(activityCodes);

        return poster;
    }
}
