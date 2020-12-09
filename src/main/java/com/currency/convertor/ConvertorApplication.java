package com.currency.convertor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConvertorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertorApplication.class, args);
    }

}
