package icu.bleuweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EMailApplication.class, args);
    }
}
