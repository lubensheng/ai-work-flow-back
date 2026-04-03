package com.example.aiworkflowback.utils;

import com.example.aiworkflowback.Message;

public class ReturnMessageUtils {
  public static <T> Message<T> getResponse(Integer code, String message, T data) {
    Message<T> response;
    response = new Message<>(code, message, data);
    return  response;
  }
}
