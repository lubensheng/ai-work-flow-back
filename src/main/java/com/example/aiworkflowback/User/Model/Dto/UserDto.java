package com.example.aiworkflowback.User.Model.Dto;

public class UserDto {
  private String userName;
  private String password;

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public void UserDot(String userName, String password) {
    this.password = password;
    this.userName = userName;
  }
}
