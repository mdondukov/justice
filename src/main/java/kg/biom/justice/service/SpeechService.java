package kg.biom.justice.service;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.SpeechDto;

import java.util.List;

public interface SpeechService {
    List<SpeechDto> getSpeeches(int count, AppContext context);
}
