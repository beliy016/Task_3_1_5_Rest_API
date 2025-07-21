package ru.belov.spring.restclient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.belov.spring.restclient.configs.Config;
import ru.belov.spring.restclient.entity.User;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Communication communication = context.getBean("communication", Communication.class);

        String sessionID = communication.allUsers();

        User user1 = new User(3l, "James", "Brown", (byte) 33);
        User user2 = new User(3l, "Thomas", "Shelby", (byte) 33);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionID);

        HttpEntity<User> request = new HttpEntity<>(user1, headers);

        communication.saveUser(user1, request);

        HttpEntity<User> request2 = new HttpEntity<>(user2, headers);

        communication.updateUser(user2, request2);

        communication.deleteUser(3l, request2);
    }
}
