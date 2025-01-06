package kg.biom.justice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String home(Model model) {
        return "admin/pages/dash";
    }

    @GetMapping("/events")
    public String getEventsPage(Model model) {
        return "admin/pages/events";
    }
}
