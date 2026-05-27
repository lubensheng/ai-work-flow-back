package com.example.aiworkflowback.Flow.Modal.Dto;

import com.example.aiworkflowback.LlmConfig.Modal.Entity.LLMConfig;
import lombok.Data;

@Data
public class NodeConfig {
  // 当前节点的字段 ，可以用做环境变量
  public NodeField[] fields;
  // 条件节点的条件配置
  public ConditionItem[] conditions;
  public LLMConfig llmApiConfig;
}
