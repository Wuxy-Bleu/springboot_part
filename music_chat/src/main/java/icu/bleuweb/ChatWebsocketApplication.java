package icu.bleuweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class ChatWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatWebsocketApplication.class, args);
    }
}
