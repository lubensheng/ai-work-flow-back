package com.example.aiworkflowback.Flow.Modal.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
*
* id: string;
  source: string;
  target: string;
  sourceHandle: string;
  type: "workFlowEdge";
  data: {
    active: boolean;
    mouseIn: boolean;
    showRelateNode: boolean;
    currentEdgeInfo: {
      source: string;
      target: string;
    };
  };
*
* */

@Data
public class EdgeItem {

  @NotBlank(message = "id 不能为空")
  public String id;

  @NotBlank(message = "连接线源节点id不能为空")
  public String source;

  @NotBlank(message = "连接线目标节点id不能为空")
  public String target;

  @NotBlank(message = "连接线源节点HandleId不能为空")
  public String sourceHandle;

  @NotBlank(message = "连接线源类型不能为空")
  public String type;

  public EdgeData data;
}
