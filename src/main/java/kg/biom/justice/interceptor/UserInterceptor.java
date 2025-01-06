package kg.biom.justice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.biom.justice.model.UserUtil;
import kg.biom.justice.model.entity.UserEntity;
import kg.biom.justice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        if (modelAndView == null) return;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userRepository.findByUsername(userDetails.getUsername())
                    .ifPresent(entity -> modelAndView.addObject("user", getUser(entity)));
        }
    }

    private User getUser(UserEntity entity) {
        String initials = UserUtil.getInitials(entity.getFirstName(), entity.getLastName(), entity.getUsername());
        return new User(entity.getFirstName(), entity.getLastName(), entity.getUsername(), initials);
    }

    private record User(String firstName, String lastName, String username, String initials) {
    }
}
