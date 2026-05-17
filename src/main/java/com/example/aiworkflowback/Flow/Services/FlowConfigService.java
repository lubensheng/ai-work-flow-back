package com.example.aiworkflowback.Flow.Services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;

public interface FlowConfigService {
  void saveFlowConfig(FlowConfigEntity value);
}
