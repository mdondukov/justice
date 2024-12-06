package kg.biom.justice.service;

import kg.biom.justice.model.dto.PageDto;

import java.util.Locale;

/**
 * Сервис для работы со статическими страницами
 */
public interface PageService {
    PageDto getPage(String slug, Locale locale);
}
