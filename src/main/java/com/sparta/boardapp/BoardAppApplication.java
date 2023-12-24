package com.sparta.boardapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //jpa auditing 기술을 사용하겠다
@SpringBootApplication
public class BoardAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardAppApplication.class, args);
    }

}
