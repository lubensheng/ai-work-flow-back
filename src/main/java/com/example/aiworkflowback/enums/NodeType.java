package com.example.aiworkflowback.enums;

import lombok.Getter;

@Getter
public enum NodeType {
  START_NODE,
  END_NODE,
  LLM_NODE,
  CONDITION_NODE,
  ANNOTATION_NODE,
  AGENT_NODE;

  public String getValue() {
    return this.name();
  }
}
