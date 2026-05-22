package com.example.aiworkflowback.enums;

import lombok.Getter;

@Getter
public enum NodeType {
  START_NODE;

  public String getValue() {
    return this.name();
  }
}
