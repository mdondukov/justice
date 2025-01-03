package kg.biom.justice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        Optional.ofNullable(request.getSession().getAttribute("error"))
                .ifPresent(error -> {
                    model.addAttribute("error", error);
                    request.getSession().removeAttribute("error");
                });

        return "admin/login";
    }

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "admin/dash";
    }
}
