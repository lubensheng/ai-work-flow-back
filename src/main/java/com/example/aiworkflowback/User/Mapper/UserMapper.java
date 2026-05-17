package com.example.aiworkflowback.User.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.User.Model.Dto.UserDto;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
  UserEntity selectUserByUserName(@Param("userName") String userName);
  void updateUserInfo(UserDto user);
  void insertUserInfo(UserDto user);
}
