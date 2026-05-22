package com.example.aiworkflowback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.example.aiworkflowback.*.Mapper")
@EnableAsync
public class AiWorkFlowBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiWorkFlowBackApplication.class, args);
    }

}
