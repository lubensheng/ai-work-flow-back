package com.example.aiworkflowback.Flow.Modal.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveFlowDto {
  @NotBlank(message = "应用名称不能为空")
  public String appName;

  @NotBlank(message = "应用名称类型不能为空")
  public String appType;

  public String appDesc;

  @NotNull(message = "连接线不能为空")
  public EdgeItem[] edgeList;

  @NotNull(message = "连接线不能为空")
  public NodeItem[] nodeList;
}
