package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowRun {

  @Autowired
  Core flowExecutorCore;

  public void run(FlowExeInstantParams flowInfo) {
    EdgeItem[] edgeList = flowInfo.getEdgeList();
    NodeItem[] nodeList = flowInfo.getNodeList();

  }
}
