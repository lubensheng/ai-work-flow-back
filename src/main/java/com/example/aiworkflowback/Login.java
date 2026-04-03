package com.example.aiworkflowback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 加上这个注解，表示这是一个控制器
public class Login {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World! 你的 Spring Boot 项目启动成功了！";
    }
}
