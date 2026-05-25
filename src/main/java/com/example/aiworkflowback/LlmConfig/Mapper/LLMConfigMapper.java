package com.example.aiworkflowback.LlmConfig.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.SaveDto;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.UpdateDto;
import com.example.aiworkflowback.LlmConfig.Modal.Entity.LLMConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LLMConfigMapper extends BaseMapper<LLMConfig> {
  void inertValues(SaveDto values);
  LLMConfig[] selectAll();
  void deleteById(@Param("id") Long id);
  void updateValue(UpdateDto values);
}
