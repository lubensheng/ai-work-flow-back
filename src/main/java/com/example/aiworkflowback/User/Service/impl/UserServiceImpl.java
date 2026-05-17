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

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserMapper userMapper;

  @Override
  public Message<UserDto> register(UserDto userInfo) {
    try {
      UserEntity user = this.userMapper.selectUserByUserName(userInfo.getUserName());
      if (user == null) {
        this.userMapper.insertUserInfo(userInfo);
      } else if (!Objects.equals(user.password, userInfo.getPassword())) {
        return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "密码错误", userInfo);
      }
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "注册成功", userInfo);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), userInfo);
    }

  }

  @Override
  public Message<UserDto> updateUserInfo(UserDto userInfo) {
    try {
      UserEntity user = this.userMapper.selectUserByUserName(userInfo.getUserName());
      if (user == null) {
        return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "用户不存在", null);
      }
      this.userMapper.updateUserInfo(userInfo);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "修改成功", null);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), userInfo);

    }
  }

  @Override
  public UserEntity selectUserInfoByUserName(String useName) {
    try {
      return this.userMapper.selectUserByUserName(useName);
    } catch (Exception e) {
      return null;
    }
  }
}
