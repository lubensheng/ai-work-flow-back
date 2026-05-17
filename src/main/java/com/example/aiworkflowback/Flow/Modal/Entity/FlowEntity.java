package com.example.aiworkflowback.Flow.Modal.Entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlowEntity {
  @TableId(type = IdType.AUTO)
  public Long id;
  public Long userId;
  public String userName;
  public String appName;
  public String appType;
  public String appDesc;
  public Long flowConfigId;
  // '流程状态 1 草稿， 2发布'
  public Integer flowStatus;
  @TableField(fill = FieldFill.INSERT)
  public LocalDateTime createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  public LocalDateTime updateTime;
}
