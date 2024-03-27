package ru.innopolis.attestation03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.innopolis.attestation03.configs.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class Attestation03Application {
    public static void main(String[] args) {
        SpringApplication.run(Attestation03Application.class, args);
    }
}
