package com.example.aiworkflowback.Flow.Modal.Dto;

import lombok.Data;

@Data
public class ConditionItem {
  public String id;
  public String  type;
  public String condition;
  // 这里缓存一下， 其实可以通过edgeList 的 target source 去查找的，这里存储方便查找
  public String handleNodeId;
}
