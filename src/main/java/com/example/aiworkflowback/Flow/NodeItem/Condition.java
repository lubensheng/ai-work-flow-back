package com.example.aiworkflowback.Flow.NodeItem;

import lombok.Data;

@Data
public class Condition {
  public String relationType;
  public String conditionValue;
  public String key;
  public String type;
}
