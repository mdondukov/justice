package kg.biom.justice.service.impl;

import kg.biom.justice.exception.NotFoundException;
import kg.biom.justice.model.dto.PageDto;
import kg.biom.justice.model.mapper.PageMapper;
import kg.biom.justice.repository.PageViewRepository;
import kg.biom.justice.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageViewRepository pageViewRepository;
    private final PageMapper pageMapper;

    @SneakyThrows
    @Override
    public PageDto getPage(String slug, Locale locale) {
        return pageViewRepository.findBySlug(slug, locale.getLanguage())
                .map(pageMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Event not found with slug: " + slug));
    }
}
