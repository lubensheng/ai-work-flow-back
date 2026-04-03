package com.example.aiworkflowback.commomModal.entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class commonEntity {
  // 创建人
  public String createBy;
  // 更新人
  public String updateBy;
  // 创建时间
  public LocalDateTime createTime;
  // 更新时间
  public LocalDateTime updateTime;
}
