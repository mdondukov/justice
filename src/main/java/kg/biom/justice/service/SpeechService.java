package kg.biom.justice.service;

import kg.biom.justice.model.dto.SpeechDto;
import org.springframework.data.domain.Page;

import java.util.Locale;

public interface SpeechService {
    Page<SpeechDto> getSpeeches(int page, int limit, Locale locale);
}
