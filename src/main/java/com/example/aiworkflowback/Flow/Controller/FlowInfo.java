package com.example.aiworkflowback.Flow.Controller;


import com.example.aiworkflowback.Flow.Modal.Dto.QueryFlow;
import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.impl.FlowSaveServiceImpl;
import com.example.aiworkflowback.Message;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow")
public class FlowInfo {

  @Resource
  FlowSaveServiceImpl flowSaveService;

  @PostMapping("/save")
  public Message<ResponseDto> saveFlowInfo(@RequestBody SaveFlowDto flowData) {
    return flowSaveService.saveFlowInfo(flowData);
  }

  @PostMapping("/queryFlowInfo")
  public Message<FlowEntity> queryFlowInfo(@RequestBody QueryFlow queryFlow) {
    return flowSaveService.queryFlowInfo(queryFlow);
  }

}
