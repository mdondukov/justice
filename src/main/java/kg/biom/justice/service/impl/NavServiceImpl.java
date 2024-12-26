package kg.biom.justice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.biom.justice.exception.NotFoundException;
import kg.biom.justice.model.dto.NavDto;
import kg.biom.justice.model.dto.NavItemDto;
import kg.biom.justice.service.NavService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NavServiceImpl implements NavService {
    private final ObjectMapper objectMapper;
    private final List<NavItemDto> main = new ArrayList<>();

    @Value("${content.nav.filename}")
    private String filename;

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    private void initMappings() {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/**/nav*.json");

        getResource(resources).ifPresent(this::prepareNav);
    }

    private Optional<Resource> getResource(Resource[] resources) {
        return Arrays.stream(resources)
                .filter(resource -> Objects.requireNonNull(resource.getFilename()).equals(filename))
                .findFirst();
    }

    @SneakyThrows
    private void prepareNav(Resource resource) {
        if (resource == null) throw new NotFoundException(String.format("Navigation file %s not found", filename));
        var nav = objectMapper.readValue(resource.getInputStream(), NavDto.class);
        main.addAll(nav.getMain());
    }

    @Override
    public List<NavItemDto> getMainNav() {
        return main;
    }
}
