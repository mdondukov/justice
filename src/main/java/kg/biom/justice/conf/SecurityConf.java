package kg.biom.justice.conf;

import kg.biom.justice.handler.AuthenticationFailureExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConf {
    private final AuthenticationFailureExceptionHandler authenticationFailureExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Отключаем CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").authenticated() // Доступ к админке только после аутентификации
                        .anyRequest().permitAll() // Публичные страницы доступны без авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Указываем кастомную страницу логина
                        .defaultSuccessUrl("/admin", true) // Перенаправление после успешного входа
                        .failureHandler(authenticationFailureExceptionHandler) // Используем кастомный обработчик
                        .permitAll() // Делаем страницу логина доступной для всех
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL выхода
                        .logoutSuccessUrl("/login") // Перенаправление после выхода
                        .permitAll() // Разрешаем доступ ко всем
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
