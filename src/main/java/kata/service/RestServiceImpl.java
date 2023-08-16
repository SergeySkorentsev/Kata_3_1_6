package kata.service;

import kata.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl implements RestService{

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public RestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCookie() {
        HttpHeaders httpHeaders = restTemplate.getForEntity(URL, String.class).getHeaders();
        return httpHeaders.getFirst(HttpHeaders.SET_COOKIE);
    }

    @Override
    public String postUser(String sessionId, User user) {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, getRequest(sessionId, user), String.class);
        System.out.println("POST: " + response.getBody());
        return response.getBody();
    }

    @Override
    public String putUser(String sessionId, User user) {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, getRequest(sessionId, user), String.class);
        System.out.println("PUT: " + response.getBody());
        return response.getBody();
    }

    @Override
    public String deleteUser(String sessionId, User user) {
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + user.getId(), HttpMethod.DELETE, getRequest(sessionId, user), String.class);
        System.out.println("DELETE: " + response.getBody());
        return response.getBody();
    }

    private HttpEntity<User> getRequest(String sessionId, User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", sessionId);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        return request;
    }
}
