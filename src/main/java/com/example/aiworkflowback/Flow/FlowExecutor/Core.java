package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.enums.NodeType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class Core {
  @Async
  public void runTask() {}

  public NodeItem getCurrentRunningNode() {
    return null;
  }

  public Optional<NodeItem> getStartNode(NodeItem[] nodeList) {
    return Arrays.stream(nodeList).filter(Objects::nonNull) .filter(nodeItem -> nodeItem.nodeType.getValue().equals(NodeType.START_NODE.getValue())).findFirst();
  }



}
