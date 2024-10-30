package kg.biom.justice.service.impl;

import kg.biom.justice.model.AppContext;
import kg.biom.justice.model.dto.SpeechDto;
import kg.biom.justice.service.SpeechService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeechServiceImpl implements SpeechService {

    @Value("${content.base.path}")
    private String basePath;

    @Override
    public List<SpeechDto> getSpeeches(int count, AppContext context) {
        return List.of(
                new SpeechDto(3L,
                        "eco-safety",
                        "Защита экологической безопасности граждан в мире и Кыргызстане: нормы и практики",
                        "Анна Кириленко",
                        String.format("%s/speeches/img/aa99d532-7de0-45e4-8b68-f7b5f578b9e3.jpg", basePath),
                        null,
                        null),
                new SpeechDto(2L,
                        "international-law-and-human-rights",
                        "Международное право в сфере прав человека и его реализации в Кыргызстане",
                        "Аннелиз Бостон",
                        String.format("%s/speeches/img/d131b4da-fd49-4cc0-b592-b61b10e809e8.jpg", basePath),
                        null,
                        null),
                new SpeechDto(1L,
                        "gender-mechanisms",
                        "Механизмы обеспечения гендерного равенства в мире и Кыргызстане",
                        "Зульфия Кочорбаева",
                        String.format("%s/speeches/img/1892ec51-6c32-4aca-a182-527f338afc83.jpg", basePath),
                        null,
                        null)
        );
    }
}
