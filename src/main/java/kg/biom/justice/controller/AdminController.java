package kg.biom.justice.controller;

import jakarta.validation.Valid;
import kg.biom.justice.model.dto.EventDto;
import kg.biom.justice.service.EventService;
import kg.biom.justice.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MessageSource messageSource;
    private final EventService eventService;
    private final FileStorageService fileStorageService;

    @Value("${content.events.limit.table}")
    private int limit;

    @Value("${content.upload.relative_path.thumb}")
    private String thumbUploadPath;

    @GetMapping
    public String home() {
        return "admin/pages/dash";
    }

    @GetMapping("/events")
    public String getEventsTable(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "") String search,
                                 @RequestParam(required = false, defaultValue = "ru") String dataLang,
                                 Model model,
                                 Locale locale) {
        Page<EventDto> eventsPage = eventService.getEvents(page, limit, search, dataLang);

        model.addAttribute("eventsPage", eventsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", search);
        model.addAttribute("dataLang", dataLang);

        model.addAttribute("paginationMessage",
                getPaginationMessage(page, limit, eventsPage.getTotalElements(), locale));

        return "admin/pages/events";
    }

    @GetMapping("/event")
    public String getEventForm(@RequestParam(required = false) Long id,
                               @RequestParam(required = false, defaultValue = "ru") String dataLang,
                               Model model) {
        EventDto event = id != null
                ? eventService.getEvent(id, dataLang)
                : eventService.getEmptyEvent(dataLang);

        model.addAttribute("event", event);

        return "admin/pages/event";
    }

    private String getPaginationMessage(int page, int limit, long total, Locale locale) {
        return messageSource.getMessage("pagination.showing",
                new Object[]{page * limit + 1, Math.min((page + 1) * limit, (int) total), total},
                locale);
    }

    @PostMapping("/event")
    public String saveEventForm(@Valid @ModelAttribute("event") EventDto event,
                                BindingResult bindingResult,
                                Model model) {
        model.addAttribute("dataLang", event.getLang());

        if (bindingResult.hasErrors()) {
            model.addAttribute("event", event);
            log.error(bindingResult.getAllErrors().toString());
            return "admin/pages/event";
        }

        log.info("saving event {}", event);

        model.addAttribute("event", event);
        model.addAttribute("successMessage", "Changes saved successfully!");

        return "admin/pages/event";
    }

    @PostMapping("/thumb/upload")
    @ResponseBody
    public ResponseEntity<String> uploadThumbnail(@RequestParam("thumbFile") MultipartFile file) {
        try {
            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                return ResponseEntity.badRequest().body("Invalid file type. Only images are allowed.");
            }

            URI fileUrl = fileStorageService.save(file, thumbUploadPath);
            log.debug("uploading thumbnail {}", fileUrl);
            return ResponseEntity.ok(fileUrl.toString());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload the file.");
        }
    }

    @PostMapping("/thumb/delete")
    @ResponseBody
    public ResponseEntity<String> deleteThumbnail(@RequestParam("fileUrl") String fileUrl) {
        try {
            fileStorageService.deleteByUri(URI.create(fileUrl), thumbUploadPath);
            log.debug("deleted thumbnail {}", fileUrl);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete the file.");
        }
    }
}
