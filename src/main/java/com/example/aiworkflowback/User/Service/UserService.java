package com.example.aiworkflowback.User.Service;

import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.User.Model.Dto.UserDto;

public interface UserService {
  Message<UserDto> register(UserDto userInfo);
}
