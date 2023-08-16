package kata.service;

import kata.model.User;

public interface RestService {
    String getCookie();
    String postUser(String sessionId, User user);
    String putUser(String sessionId, User user);
    String deleteUser(String sessionId, User user);
}
