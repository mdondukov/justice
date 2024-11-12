package kg.biom.justice.service.impl;

import kg.biom.justice.model.ContentUtil;
import kg.biom.justice.model.dto.PosterDto;
import kg.biom.justice.model.entity.PosterViewEntity;
import kg.biom.justice.repository.PosterRepository;
import kg.biom.justice.repository.PosterViewRepository;
import kg.biom.justice.service.PosterService;
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
public class PosterServiceImpl implements PosterService {
    private final PosterViewRepository posterViewRepository;
    private final PosterRepository posterRepository;

    @Value("${content.base.path}")
    private String basePath;

    @Override
    public Page<PosterDto> getPosters(int page, int limit, Locale locale) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createDate"));
        return posterViewRepository.findAllByLang(locale.getLanguage(), pageable).map(this::convertToDto);
    }

    private PosterDto convertToDto(PosterViewEntity entity) {
        PosterDto poster = new PosterDto();
        poster.setId(entity.getId());
        poster.setSlug(entity.getSlug());
        poster.setTitle(entity.getTitle());
        poster.setDescr(entity.getDescr());
        poster.setThumb(ContentUtil.mergePath(basePath, entity.getThumbnail()));

        if (entity.getFiles() != null)
            poster.setFiles(entity.getFiles().stream().map(f -> ContentUtil.handleAttachFile(f, basePath)).toList());

        List<String> activityCodes = posterRepository.findActivitySlugsByPosterId(poster.getId());
        poster.setActivityCodes(activityCodes);

        return poster;
    }
}
