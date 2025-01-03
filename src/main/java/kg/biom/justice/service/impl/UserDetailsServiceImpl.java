package kg.biom.justice.service.impl;

import kg.biom.justice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(entity -> User
                        .withUsername(entity.getUsername())
                        .password(entity.getPassword())
                        .accountLocked(!entity.isEnabled())
                        .authorities(entity.getRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
