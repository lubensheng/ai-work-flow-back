package com.example.aiworkflowback.commomModal.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class commonEntity {
  // 创建人
  public String createBy;
  // 更新人
  public String updateBy;
  // 创建时间
  @TableField(fill = FieldFill.INSERT)
  public LocalDateTime createTime;
  // 更新时间
  @TableField(fill = FieldFill.INSERT_UPDATE)
  public LocalDateTime updateTime;
}
