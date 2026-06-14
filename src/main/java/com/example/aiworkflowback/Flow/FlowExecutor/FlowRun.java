package com.example.aiworkflowback.Flow.FlowExecutor;

import com.example.aiworkflowback.Flow.Modal.Dto.EdgeItem;
import com.example.aiworkflowback.Flow.Modal.Dto.FlowContext;
import com.example.aiworkflowback.Flow.Modal.Dto.NodeItem;
import com.example.aiworkflowback.Flow.expection.NodeFindException;
import com.example.aiworkflowback.enums.NodeType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class FlowRun {
  private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(0, TimeUnit.SECONDS) // SSE长连接不设读超时
      .build();
  @Autowired
  Core flowExecutorCore;

  public void run(FlowExeInstantParams flowInfo, SseEmitter emitter, String content) throws NodeFindException, IOException, InterruptedException {
    EdgeItem[] edgeList = flowInfo.getEdgeList();
    NodeItem[] nodeList = flowInfo.getNodeList();
    NodeItem currentRunStartNode = flowExecutorCore.getStartNode(nodeList).orElse(null);
    if (currentRunStartNode == null) {
      throw new NodeFindException("查询起始节点失败");
    }
    FlowContext context = new FlowContext();
    context.setContent(content);
    while (true) {
      if (currentRunStartNode.type.getValue().equals(NodeType.END_NODE.getValue())) {
        sendSseMsg(emitter, "[结束]");

        break;
      }
      NodeItem curRunStartNode = flowExecutorCore.getCurrentRunningNode(nodeList, edgeList, currentRunStartNode);

      String apiType = curRunStartNode.data.nodeConfig.llmApiConfig.getModalType();
      String api = curRunStartNode.data.nodeConfig.llmApiConfig.getApiKey();

      String fullLlmResult = sendMsg(
          apiType,
          api,
          emitter,
          context.getContent(),
          flowExecutorCore.isLastUsefulNode(curRunStartNode, edgeList, nodeList)
      );
      context.setContent(fullLlmResult);
      currentRunStartNode = flowExecutorCore.getCurrentRunningNode(nodeList, edgeList, curRunStartNode);
    }
  }

  private void sendSseMsg(SseEmitter emitter, String data) throws IOException {
    emitter.send(SseEmitter.event()

        .data(data));
  }

  private String sendMsg(String apiKey, String apiType, SseEmitter emitter, String content, boolean isLastUsefulNode) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    StringBuilder fullContent = new StringBuilder();
    String url = getLLMRequestUrl(apiType, apiKey, content);
    Request request = new Request.Builder()
        .url(url)
        .get()
        .addHeader("Accept", "text/event-stream")
        .build();
    EventSource.Factory factory = EventSources.createFactory(okHttpClient);
    EventSource eventSource = factory.newEventSource(request, new EventSourceListener() {

      // 收到单条SSE分片数据
      @Override
      public void onEvent(EventSource eventSource, String id, String type, String data) {
        fullContent.append(data);
        try {
          if (isLastUsefulNode) {
            sendSseMsg(emitter, data);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      // 远端SSE正常关闭，当前节点执行完成
      @Override
      public void onClosed(EventSource eventSource) {
        latch.countDown();
      }

      // 接口异常断开
      @Override
      public void onFailure(EventSource eventSource, Throwable t, Response response) {
        latch.countDown();
      }
    });
    latch.await();
    eventSource.cancel();
    return fullContent.toString();
  }

  private String getLLMRequestUrl(String apiType, String apiKey, String content) {
    return "http://43.138.198.247:3000/agent/mockConversation?apiType=" + apiType + "&apiKey=" + apiKey + "&content=" + content;
  }

}
