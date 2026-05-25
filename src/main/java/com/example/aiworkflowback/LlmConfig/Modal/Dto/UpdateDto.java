package com.example.aiworkflowback.LlmConfig.Modal.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateDto {
  @NotNull(message = "id不能为空")
  private Long id;
  @NotNull(message = "modalType不能为空")
  private String modalType;
  @NotNull(message = "apiKey不能为空")
  private String apiKey;
}
