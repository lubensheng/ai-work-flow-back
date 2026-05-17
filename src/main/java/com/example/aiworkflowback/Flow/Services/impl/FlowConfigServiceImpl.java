package com.example.aiworkflowback.Flow.Services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Mapper.FlowConfigMapper;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;
import com.example.aiworkflowback.Flow.Services.FlowConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class FlowConfigServiceImpl implements FlowConfigService {

  @Resource
  FlowConfigMapper flowConfigMapper;

  @Override
  public void saveFlowConfig(FlowConfigEntity value) {
    this.flowConfigMapper.insertValue(value);
  }


}
