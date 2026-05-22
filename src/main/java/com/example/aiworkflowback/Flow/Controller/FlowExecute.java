package com.example.aiworkflowback.Flow.Controller;

import com.alibaba.fastjson2.JSON;
import com.example.aiworkflowback.Flow.FlowExecutor.FlowExeInstantParams;
import com.example.aiworkflowback.Flow.FlowExecutor.FlowRun;
import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.impl.FlowConfigServiceImpl;
import com.example.aiworkflowback.Flow.Services.impl.FlowSaveServiceImpl;
import com.example.aiworkflowback.Message;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flowExecute")
public class FlowExecute {
  @Resource
  FlowSaveServiceImpl flowSaveService;

  @Resource
  FlowConfigServiceImpl flowConfigService;

  @Resource
  FlowRun flowRun;

  @PostMapping("/executeFlow/{flowId}")
  public void executeFlow(@PathVariable String flowId) {
    FlowEntity flow = this.flowSaveService.queryFlowInfoById(flowId);
    if (flow != null) {
      Message<FlowConfigEntity> flowConfigEntityMessage = this.flowConfigService.queryFlowConfigInfo(String.valueOf(flow.flowConfigId));
      FlowConfigEntity flowConfig = flowConfigEntityMessage.getData();
      FlowExeInstantParams p = new FlowExeInstantParams();
      p.setAppName(flow.getAppName());
      p.setAppType(flow.getAppType());
      p.setEdgeList((EdgeItem[]) JSON.toJSON(flowConfig.getEdgeList()));
      p.setNodeList((NodeItem[]) JSON.toJSON(flowConfig.getNodeList()));
      this.flowRun.run(p);
    }
  }
}
