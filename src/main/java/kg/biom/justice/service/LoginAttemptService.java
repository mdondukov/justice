package kg.biom.justice.service;

public interface LoginAttemptService {

    void loginFailed(String username);

    void loginSucceeded(String username);
}
