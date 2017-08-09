package com.quickapi.userAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static long TOKEN_DURATION_IN_SECONDS = 60*5;
    public static long TOKEN_CHECK_DURATION_IN_MILISECONDS = 15000;
    public static long LOGGEDUSER_5Minutes_INTERVAL=5;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
