package com.example.aiworkflowback.LlmConfig.Modal.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveDto {
  @NotNull(message = "modalType不能为空")
  private String modalType;
  @NotNull(message = "apiKey不能为空")
  private String apiKey;
}
