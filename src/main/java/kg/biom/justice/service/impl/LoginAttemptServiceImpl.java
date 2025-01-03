package kg.biom.justice.service.impl;

import kg.biom.justice.repository.UserRepository;
import kg.biom.justice.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginAttemptServiceImpl implements LoginAttemptService {
    private final UserRepository userRepository;

    @Value("${user.max_attempts}")
    private int maxAttempts;

    @Transactional
    @Override
    public void loginFailed(String username) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    int attempts = user.getFailedAttempts() + 1;
                    user.setFailedAttempts(attempts);
                    user.setEnabled(attempts < maxAttempts);
                    userRepository.save(user);
                });
    }

    @Transactional
    @Override
    public void loginSucceeded(String username) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    user.setFailedAttempts(0);
                    userRepository.save(user);
                });
    }
}
