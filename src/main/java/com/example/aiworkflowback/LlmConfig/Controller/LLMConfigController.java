package com.example.aiworkflowback.LlmConfig.Controller;

import com.example.aiworkflowback.LlmConfig.Modal.Dto.SaveDto;
import com.example.aiworkflowback.LlmConfig.Modal.Dto.UpdateDto;
import com.example.aiworkflowback.LlmConfig.Modal.Entity.LLMConfig;
import com.example.aiworkflowback.LlmConfig.Services.impl.LLMConfigServiceImpl;
import com.example.aiworkflowback.Message;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/llmConfig")
public class LLMConfigController {
  @Resource
  LLMConfigServiceImpl llmConfigService;

  @PostMapping("/addConfig")
  Message<String> addConfig(@RequestBody SaveDto value) {
    return llmConfigService.insertValue(value);
  }

  @PostMapping("/updateConfig")
  Message<String> updateConfig(@RequestBody UpdateDto value) {
    return llmConfigService.updateValue(value);
  }

  @PostMapping("/deleteConfig/{id}")
  Message<String> deleteConfig(@PathVariable Long id) {
    return llmConfigService.deleteValue(id);
  }

  @GetMapping("/queryAll")
  Message<LLMConfig[]> queryAll() {
    return llmConfigService.queryAll();
  }
}
