package com.example.aiworkflowback.Flow.Modal.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatReq {
  private String prompt;
  private List<ChatMessage> history; // 历史对话
}
