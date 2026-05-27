package com.example.aiworkflowback.Flow.Controller;


import com.example.aiworkflowback.Flow.Modal.Dto.QueryFlow;
import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.ReturnFlowInfo;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.impl.FlowConfigServiceImpl;
import com.example.aiworkflowback.Flow.Services.impl.FlowSaveServiceImpl;
import com.example.aiworkflowback.Message;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow")
public class FlowInfo {

  @Resource
  FlowSaveServiceImpl flowSaveService;

  @Resource
  FlowConfigServiceImpl flowConfigService;

  @PostMapping("/save")
  public Message<ResponseDto> saveFlowInfo(@RequestBody SaveFlowDto flowData) {
    return flowSaveService.saveFlowInfo(flowData);
  }

  @PostMapping("/queryFlowInfo")
  public Message<ReturnFlowInfo> queryFlowInfo(@RequestBody QueryFlow queryFlow) {
    return flowSaveService.queryFlowInfo(queryFlow);
  }

  @PostMapping("/queryFlowConfigInfo/{flowConfigId}")
  public Message<FlowConfigEntity> queryFlowConfigInfo(@PathVariable String flowConfigId) {
    return this.flowConfigService.queryFlowConfigInfo(flowConfigId);
  }
}
