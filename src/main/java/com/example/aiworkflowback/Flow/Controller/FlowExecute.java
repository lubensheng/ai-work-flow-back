package com.example.aiworkflowback.Flow.Controller;

import com.example.aiworkflowback.Flow.Services.impl.FlowConfigServiceImpl;
import com.example.aiworkflowback.Flow.Services.impl.FlowSaveServiceImpl;
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

  @PostMapping("/executeFlow/{flowId}")
  public void executeFlow(@PathVariable String flowId) {

  }
}
