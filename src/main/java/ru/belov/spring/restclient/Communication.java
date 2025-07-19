package ru.belov.spring.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.belov.spring.restclient.entity.User;
import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";


    public String allUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        return responseEntity.getHeaders().getFirst("Set-Cookie");
    }

    public void saveUser(User user, HttpEntity<User> request) {
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(URL, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(User user, HttpEntity<User> request) {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.PUT, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(Long id, HttpEntity<User> request) {
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, request, String.class);
        System.out.println(responseEntity.getBody());
    }
}
