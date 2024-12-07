package com.example.demo_dzq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.demo_dzq.mapper")
@SpringBootApplication(scanBasePackages = "com.example.demo_dzq")
public class DemoDzqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDzqApplication.class, args);
    }

}
