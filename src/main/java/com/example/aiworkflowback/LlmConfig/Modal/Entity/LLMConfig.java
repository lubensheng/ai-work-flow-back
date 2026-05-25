package com.example.aiworkflowback.LlmConfig.Modal.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class LLMConfig {
  @TableId(type = IdType.AUTO)
  private Long id;
  private String modalType;
  private String apiKey;
}
