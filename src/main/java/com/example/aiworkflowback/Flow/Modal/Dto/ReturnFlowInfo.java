package com.example.aiworkflowback.Flow.Modal.Dto;

import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import lombok.Data;

@Data
public class ReturnFlowInfo {
  public FlowEntity[] resultData;
  public int total;
}
