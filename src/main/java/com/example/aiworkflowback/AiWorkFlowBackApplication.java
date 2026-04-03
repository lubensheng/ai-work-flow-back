package com.example.aiworkflowback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.aiworkflowback.*.Mapper")
public class AiWorkFlowBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiWorkFlowBackApplication.class, args);
    }

}
