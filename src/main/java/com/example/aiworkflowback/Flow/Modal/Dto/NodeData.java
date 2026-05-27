package com.example.aiworkflowback.Flow.Modal.Dto;

import lombok.Data;

@Data
public class NodeData {
  public String[] childrenIds;
  public int label;
  public String title;
  public boolean notParent;
  public boolean select;
  public NodeConfig nodeConfig;
}
