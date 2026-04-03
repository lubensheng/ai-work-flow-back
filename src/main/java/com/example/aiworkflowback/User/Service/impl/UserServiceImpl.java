package com.example.aiworkflowback.User.Service.impl;

import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import com.example.aiworkflowback.User.Mapper.UserMapper;
import com.example.aiworkflowback.User.Model.Dto.UserDto;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import com.example.aiworkflowback.User.Service.UserService;
import com.example.aiworkflowback.enums.HttpCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserMapper userMapper;

  @Override
  public Message<UserDto> register(UserDto userInfo) {
    try {
      UserEntity user = this.userMapper.selectUserByUserName(userInfo.getUserName(), userInfo.getPassword());
      if (user == null) {
        this.userMapper.insertUserInfo(userInfo);
      }
      return ReturnMessageUtils.<UserDto>getResponse(HttpCode.SUCCESS_CODE.getCode(), "注册成功", userInfo);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), userInfo);
    }

  }
}
