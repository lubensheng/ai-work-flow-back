package com.example.aiworkflowback.Flow.NodeItem;

import lombok.Data;

@Data
public class ConditionNode {
  public String id;
  public String type;
  public Condition condition;
  // 这里缓存一下， 其实可以通过edgeList 的 target source 去查找的，这里存储方便查找
  public String handleNodeId;
}
