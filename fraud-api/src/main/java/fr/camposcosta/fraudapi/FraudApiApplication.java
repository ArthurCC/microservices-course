package fr.camposcosta.fraudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudApiApplication.class, args);
    }
}
