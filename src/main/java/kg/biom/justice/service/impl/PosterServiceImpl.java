package kg.biom.justice.service.impl;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.PosterDto;
import kg.biom.justice.service.PosterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PosterServiceImpl implements PosterService {

    @Value("${content.base.path}")
    private String basePath;

    @Override
    public List<PosterDto> getPosters(int count, AppContext context) {
        return List.of(
                new PosterDto(3L,
                        "eco-public-participation",
                        "Как я могу вовлекаться в принятие значимых экологических решений?",
                        String.format("%s/posters/img/5201076d-9f0d-4016-847f-05d2ef046a90.jpg", basePath),
                        Collections.emptyList()),
                new PosterDto(2L,
                        "public-law-process",
                        "Участие гражданского общества в законотворческом процессе, а также в оценке системы защиты прав человека",
                        String.format("%s/posters/img/b2d29749-9d36-44cc-909b-baa6de1a6c9f.jpg", basePath),
                        Collections.emptyList()),
                new PosterDto(1L,
                        "private-data-safety",
                        "Карта защиты персональных данных",
                        String.format("%s/posters/img/e955a40d-e878-4b0c-b5ae-14ae8cfac2b6.jpg", basePath),
                        Collections.emptyList())
        );
    }
}
