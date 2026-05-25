package com.example.aiworkflowback.LlmConfig.Services.impl;

import com.example.aiworkflowback.LlmConfig.Mapper.LLMConfigMapper;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.SaveDto;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.UpdateDto;
import com.example.aiworkflowback.LlmConfig.Modal.Entity.LLMConfig;
import com.example.aiworkflowback.LlmConfig.Services.LLMConfigService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LLMConfigServiceImpl implements LLMConfigService {

  @Resource
  LLMConfigMapper llmConfigMapper;

  @Override
  public Message<String> insertValue(SaveDto value) {
    try {
      this.llmConfigMapper.inertValues(value);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", "success");
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }

  @Override
  public Message<LLMConfig[]> queryAll() {
    try {
      LLMConfig[] result = this.llmConfigMapper.selectAll();
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", result);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }

  @Override
  public Message<String> updateValue(UpdateDto value) {
    try {
      this.llmConfigMapper.updateValue(value);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", "更新成功");
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }

  @Override
  public Message<String> deleteValue(Long id) {
    try {
      this.llmConfigMapper.deleteById(id);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", "删除成功");
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }
}
