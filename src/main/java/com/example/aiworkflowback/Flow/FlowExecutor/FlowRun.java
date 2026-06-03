package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.expection.NodeFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class FlowRun {
  private final ExecutorService pool = Executors.newCachedThreadPool();
  @Autowired
  Core flowExecutorCore;

  public void run(FlowExeInstantParams flowInfo) throws NodeFindException {
    EdgeItem[] edgeList = flowInfo.getEdgeList();
    NodeItem[] nodeList = flowInfo.getNodeList();
    NodeItem currentStartNode = flowExecutorCore.getStartNode(nodeList).orElse(null);
    if (currentStartNode == null) {
      throw new NodeFindException("查询起始节点失败");
    }
    for (EdgeItem e: edgeList) {
      NodeItem nextNode;
    }
  }
}
