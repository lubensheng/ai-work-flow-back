package com.example.aiworkflowback;

public class ReturnMessageUtils {
  public static <T> Message<T> getResponse( String code, String message, T data) {
    Message<T> response;
    response = new Message<T>(code, message, data);
    return  response;
  }
}
