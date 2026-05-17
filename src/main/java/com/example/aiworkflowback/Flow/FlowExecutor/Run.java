package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Run {

  @Autowired
  Core flowExecutorCore;

  public void run(SaveFlowDto flowInfo) {
    EdgeItem[] edgeList = flowInfo.edgeList;
    NodeItem[] nodeList = flowInfo.nodeList;

  }
}
