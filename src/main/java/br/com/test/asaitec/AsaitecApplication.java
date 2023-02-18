package br.com.test.asaitec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class AsaitecApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AsaitecApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/asaitec"));
        app.run(args);
    }

}
