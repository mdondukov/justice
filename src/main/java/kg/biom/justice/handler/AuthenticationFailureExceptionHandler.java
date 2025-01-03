package kg.biom.justice.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationFailureExceptionHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String username = request.getParameter("username");
        String message = exception.getMessage();

        switch (exception) {
            case LockedException ignored -> {
                log.warn("Login attempt by a locked user {}", username);
                message = "Ваш аккаунт заблокирован. Свяжитесь с администратором.";
            }

            case BadCredentialsException ignored -> {
                log.info("Login attempt with incorrect credentials for user {}", username);
                message = "Неверный логин или пароль";
            }

            default -> {
                log.error("Authentication error for user {}: {}", username, message);
                message = "Ошибка аутентификации. Свяжитесь с администратором.";
            }
        }

        request.getSession().setAttribute("error", message);
        response.sendRedirect("/login");

    }
}
