package kg.biom.justice.conf;

import kg.biom.justice.interceptor.NavInterceptor;
import kg.biom.justice.interceptor.UserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConf implements WebMvcConfigurer {
    private final LocaleChangeInterceptor localeChangeInterceptor;
    private final NavInterceptor navInterceptor;
    private final UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(navInterceptor);
        registry.addInterceptor(userInterceptor);
    }
}
