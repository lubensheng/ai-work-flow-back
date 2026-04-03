package com.example.aiworkflowback.User.Model.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.aiworkflowback.commomModal.entity.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends commonEntity {
  @TableId(type = IdType.AUTO)
  public Long id;
  public String userName;
  public String password;
}
