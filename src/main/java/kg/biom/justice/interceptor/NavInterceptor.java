package kg.biom.justice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.biom.justice.model.dto.NavItemDto;
import kg.biom.justice.service.NavService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class NavInterceptor implements HandlerInterceptor {
    private final NavService navService;
    private final MessageSource messageSource;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        if (modelAndView == null) return;

        Locale locale = RequestContextUtils.getLocale(request);
        List<NavItemDto> result = navService.getMainNav().stream()
                .map(item -> localize(item, locale))
                .toList();

        modelAndView.addObject("mainNav", result);
    }

    private NavItemDto localize(NavItemDto item, Locale locale) {
        NavItemDto result = new NavItemDto();
        result.setTitle(messageSource.getMessage(item.getTitle(), null, locale));
        result.setUrl(item.getUrl());
        if (item.getSub() != null) result.setSub(item.getSub().stream()
                .map(s -> localize(s, locale))
                .toList());
        return result;
    }
}
