package com.example.aiworkflowback.Flow.Services;

import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.CreateConversationReq;
import com.example.aiworkflowback.Message;

public interface ConversationService {
  Message<String> createConversationId(CreateConversationReq req);
}
