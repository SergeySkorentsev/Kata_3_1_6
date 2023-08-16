package kata;

import kata.model.User;
import kata.service.RestService;
import kata.service.RestServiceImpl;
import org.springframework.web.client.RestTemplate;

public class App
{
    public static void main( String[] args ) {
        StringBuilder key = new StringBuilder();
        User user = new User(3L, "James", "Brown", (byte)30);
        RestTemplate restTemplate = new RestTemplate();
        RestService restService = new RestServiceImpl(restTemplate);
        String sessionId = restService.getCookie();
        key.append(restService.postUser(sessionId, user));
        user.setName("Thomas");
        user.setLastName("Shelby");
        key.append(restService.putUser(sessionId, user));
        key.append(restService.deleteUser(sessionId, user));
        System.out.println("KEY: " + key);
    }
}
