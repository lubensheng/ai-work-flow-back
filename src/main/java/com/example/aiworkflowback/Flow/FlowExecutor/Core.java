package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.NodeItem.ConditionNode;
import com.example.aiworkflowback.enums.NodeType;
import jakarta.validation.constraints.Null;
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

  public static ConditionNode buildConditionNode() {
    ConditionNode conditionNode = new ConditionNode();
    return conditionNode;
  }


  public NodeItem getCurrentRunningNode(NodeItem[] nodeList, EdgeItem[] edgeList, NodeItem currentNode) {
    Optional<EdgeItem> currentEdgeItem = Arrays.stream(edgeList).filter(edgeItem -> edgeItem.source.equals(currentNode.id)).findFirst();
    if (currentEdgeItem.isEmpty()) {
      return null;
    }

    Optional<NodeItem> currentRunNode = Arrays.stream(nodeList).filter(nodeItem -> nodeItem.id.equals(currentEdgeItem.get().target)).findFirst();
    return currentRunNode.orElse(null);
  }

  public Optional<NodeItem> getStartNode(NodeItem[] nodeList) {
    return Arrays.stream(nodeList).filter(Objects::nonNull) .filter(nodeItem -> nodeItem.type.getValue().equals(NodeType.START_NODE.getValue())).findFirst();
  }

  public boolean isLastUsefulNode(NodeItem currentRunNode, EdgeItem[] edgeList, NodeItem[] nodeList) {
    Optional<EdgeItem> currentEdge = Arrays.stream(edgeList).filter(edgeItem -> edgeItem.source.equals(currentRunNode.id)).findFirst();
    if (currentEdge.isEmpty()) {
      return true;
    }
    Optional<NodeItem> nextNode = Arrays.stream(nodeList).filter(nodeItem -> nodeItem.id.equals(currentEdge.get().getTarget())).findFirst();
    if (nextNode.isEmpty()) {
      return true;
    }
    return nextNode.get().type.getValue().equals(NodeType.END_NODE.getValue());
  }

  public NodeItem getNextNodeByCondition(NodeItem nodeItem, EdgeItem[] edgeList, NodeItem[] nodeList, String currentContent) {


    return null;
  }

}
