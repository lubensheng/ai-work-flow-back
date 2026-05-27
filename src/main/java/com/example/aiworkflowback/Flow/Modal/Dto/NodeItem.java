package com.example.aiworkflowback.Flow.Modal.Dto;

import com.example.aiworkflowback.enums.NodeType;
import lombok.Data;

@Data
public class NodeItem {
  public NodeType nodeType;
  public String type;
  public String id;
  public String dragHandle;
  public Position position;
  public NodeData data;
}
