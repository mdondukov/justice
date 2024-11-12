package kg.biom.justice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Value("${locale.default}")
    private String defaultLocale;

    @Test
    void getEvents() {
        var locale = Locale.forLanguageTag(defaultLocale);
        var result = assertDoesNotThrow(() -> eventService.getEvents(0, 3, locale));
        result.forEach(System.out::println);
    }
}