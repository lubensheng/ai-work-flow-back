package com.example.aiworkflowback;

public class Message<T> {
  private String code;
  private String message;
  private T data;

  public Message(String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public T getData() {
    return data;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setData(T data) {
    this.data = data;
  }
}
