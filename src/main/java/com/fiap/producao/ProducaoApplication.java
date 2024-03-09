package com.fiap.producao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ProducaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducaoApplication.class, args);
    }

}