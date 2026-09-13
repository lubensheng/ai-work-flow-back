package com.example.aiworkflowback.Flow.Services;

import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.CreateConversationReq;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.HistoryConversationInfo;
import com.example.aiworkflowback.Message;

public interface ConversationService {
  Message<String> createConversationId(CreateConversationReq req);
  Message<HistoryConversationInfo[]> getHistoryConversationInfByFlowId(String flowId, String userName);
}
