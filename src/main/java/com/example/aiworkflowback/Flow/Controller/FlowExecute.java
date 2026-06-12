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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

@RestController()
@RequestMapping("/flowExecute")
public class FlowExecute {
  private final ExecutorService pool = new ThreadPoolExecutor(5,
      10,
      60L, TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(100),
      Executors.defaultThreadFactory(),
      new ThreadPoolExecutor.AbortPolicy()
  );
  @Resource
  FlowSaveServiceImpl flowSaveService;

  @Resource
  FlowConfigServiceImpl flowConfigService;

  @Resource
  FlowRun flowRun;

  @PostMapping(value = "/executeFlow/{flowId}", produces = "text/event-stream;charset=UTF-8")
  public SseEmitter executeFlow(@PathVariable String flowId) {
    SseEmitter emitter = new SseEmitter(0L);
    pool.execute(() -> {
      try {
        FlowEntity flow = this.flowSaveService.queryFlowInfoById(flowId);
        if (flow != null) {
          Message<FlowConfigEntity> flowConfigEntityMessage = this.flowConfigService.queryFlowConfigInfo(String.valueOf(flow.flowConfigId));
          FlowConfigEntity flowConfig = flowConfigEntityMessage.getData();
          FlowExeInstantParams p = new FlowExeInstantParams();
          p.setAppName(flow.getAppName());
          p.setAppType(flow.getAppType());
          List<EdgeItem> edgeList = JSON.parseArray(flowConfig.getEdgeList(), EdgeItem.class);
          p.setEdgeList(edgeList.toArray(EdgeItem[]::new));
          List<NodeItem> nodeItems = JSON.parseArray(flowConfig.getNodeList(), NodeItem.class);
          p.setNodeList(nodeItems.toArray(NodeItem[]::new));
          FlowExecute.this.flowRun.run(p, emitter);
        }
      } catch (Exception e) {
        try {
          emitter.send(SseEmitter.event().name("flow-execute").data(e.getMessage()));
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        throw new RuntimeException(e);
      }
    });
    emitter.onCompletion(emitter::complete);
    emitter.onError(e -> emitter.complete());
    emitter.onTimeout(emitter::complete);
    return emitter;
  }
}
