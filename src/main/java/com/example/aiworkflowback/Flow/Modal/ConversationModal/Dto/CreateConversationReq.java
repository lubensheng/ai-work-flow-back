package com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateConversationReq {
  @NotBlank(message = "flowId必传")
  public String flowId;
  @NotBlank(message = "userId必传")
  public String userId;
  @NotBlank(message = "userName必传")
  public String userName;
}
