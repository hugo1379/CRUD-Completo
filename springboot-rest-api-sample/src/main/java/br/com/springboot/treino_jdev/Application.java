package br.com.springboot.treino_jdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * Spring Boot application starter class
 */
@EntityScan(basePackages = "br.com.springboot.treino_jdev.model")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);/*linha principal que roda o projeto*/
    }
}
