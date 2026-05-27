package com.example.aiworkflowback.Flow.Modal.Dto;

import lombok.Data;

@Data
public class NodeField {
  public String key;
  public String fieldType;
  public String name;
  public String showName;
  public int maxLength;
  public String defaultValue;
  public boolean isSystemField;
}
