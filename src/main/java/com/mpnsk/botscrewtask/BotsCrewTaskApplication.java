package com.mpnsk.botscrewtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BotsCrewTaskApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BotsCrewTaskApplication.class, args);
        UniversityApp app = context.getBean(UniversityApp.class);

        app.run();
    }
}
