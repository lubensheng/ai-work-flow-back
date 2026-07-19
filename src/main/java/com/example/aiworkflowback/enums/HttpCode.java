package com.example.aiworkflowback.enums;

import lombok.Getter;

@Getter
public enum HttpCode {
  SUCCESS_CODE("成功", 0, "成功code"),
  ERROR_CODE("失败", -1, "失败code"),
  NOT_DATA_CODE("成功", 2, "没有数据");

  private final String name;
  private final int code;
  private final String desc;

  HttpCode(String name, int code, String desc) {
    this.name = name;
    this.code = code;
    this.desc = desc;
  }
}
