package com.example.aiworkflowback.enums;

public enum HttpCode {
  SUCCESS_CODE("成功", 0, "成功code"),
  ERROR_CODE("失败", -1, "失败code");

  private final String name;
  private final int code;
  private final String desc;

  HttpCode(String name, int code, String desc) {
    this.name = name;
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public String getName() {
    return name;
  }

}
