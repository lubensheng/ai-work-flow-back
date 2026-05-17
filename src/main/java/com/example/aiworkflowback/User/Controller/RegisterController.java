package com.example.aiworkflowback.User.Controller;

import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.User.Model.Dto.UserDto;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import com.example.aiworkflowback.User.Service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RegisterController {

  @Resource
  UserServiceImpl userService;

  @PostMapping("/regiter")
  public Message<UserDto> register(@RequestBody UserDto user) {
    return this.userService.register(user);
  }

  @PostMapping("/updateUserInfo")
  public Message<UserDto> updateUserInfo(@RequestBody UserDto user) {
    return this.userService.updateUserInfo(user);
  }
}
