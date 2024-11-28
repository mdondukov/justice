package kg.biom.justice.service;

import kg.biom.justice.model.dto.PosterDto;
import org.springframework.data.domain.Page;

import java.util.Locale;

public interface PosterService {
    Page<PosterDto> getPosters(int page, int limit, Locale locale);

    PosterDto getPoster(String slug, Locale locale);
}
