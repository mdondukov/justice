package kg.biom.justice.service;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.PosterDto;

import java.util.List;

public interface PosterService {
    List<PosterDto> getPosters(int count, AppContext context);
}
