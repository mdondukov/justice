package kg.biom.justice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/subgrants")
@RequiredArgsConstructor
public class SubgrantsController {
    private final MessageSource messageSource;

    @GetMapping
    public String about(Model model, Locale locale) {
        return "pages/subgrants";
    }
}
