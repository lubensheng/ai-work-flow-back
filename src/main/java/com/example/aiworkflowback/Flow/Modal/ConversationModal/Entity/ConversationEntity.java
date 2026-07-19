package com.example.aiworkflowback.Flow.Modal.ConversationModal.Entity;

import lombok.Data;

@Data
public class ConversationEntity {
  public Long conversationId;
  public String flowId;
  public String userId;
  public String userName;
}
