package com.example.aiworkflowback.User.Service;

import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.User.Model.Dto.UserDto;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserService {
  Message<UserDto> register(UserDto userInfo);
  Message<UserDto> updateUserInfo(UserDto userInfo);
  UserEntity selectUserInfoByUserName(String useName);
}
