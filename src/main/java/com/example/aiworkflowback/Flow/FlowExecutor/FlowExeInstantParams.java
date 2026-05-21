package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;

import lombok.Data;

@Data
public class FlowExeInstantParams {
  private String appName;
  private String appType;
  private EdgeItem[] edgeList;
  private NodeItem[] nodeList;
}
