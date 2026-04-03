package com.example.aiworkflowback;

import lombok.Data;

@Data
public class Message<T> {
  private Integer code;
  private String message;
  private T data;

  public Message(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
}
