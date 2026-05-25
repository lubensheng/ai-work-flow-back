package com.example.aiworkflowback.LlmConfig.Services;

import com.example.aiworkflowback.LlmConfig.Modal.Dto.SaveDto;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.UpdateDto;
import com.example.aiworkflowback.LlmConfig.Modal.Entity.LLMConfig;
import com.example.aiworkflowback.Message;

public interface LLMConfigService {
  Message<String> insertValue(SaveDto value);
  Message<LLMConfig[]> queryAll();
  Message<String> updateValue(UpdateDto value);
  Message<String> deleteValue(Long id);
}
