package com.example.aiworkflowback.User.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RegisterController {

  @PostMapping("/regiter")
  public String register() {
    return "注册成功";
  }
}
