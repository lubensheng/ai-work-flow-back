package com.example.aiworkflowback.Flow.Modal.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlowConfigEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long flowConfigId;
  public String edgeList;
  public String nodeList;
  @TableField(fill = FieldFill.INSERT)
  public LocalDateTime createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  public LocalDateTime updateTime;
}
