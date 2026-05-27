package com.example.aiworkflowback.Flow.Modal.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlowConfigEntity {
  @TableId(type = IdType.ASSIGN_ID)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long flowConfigId;
  public String edgeList;
  public String nodeList;
  @TableField(fill = FieldFill.INSERT)
  public LocalDateTime createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  public LocalDateTime updateTime;
}
