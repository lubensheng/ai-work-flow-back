package com.example.aiworkflowback.User.Service.impl;

import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.ReturnMessageUtils;
import com.example.aiworkflowback.User.Model.Dto.UserDto;
import com.example.aiworkflowback.User.Service.UserService;

public class UserServiceImpl implements UserService {

  @Override
  public Message<UserDto> register(UserDto userInfo) {

    return ReturnMessageUtils.<UserDto>getResponse("1", "注册成功", userInfo);
  }
}
