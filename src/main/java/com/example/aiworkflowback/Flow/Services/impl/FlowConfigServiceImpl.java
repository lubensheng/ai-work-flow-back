package com.example.aiworkflowback.Flow.Services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Mapper.FlowConfigMapper;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;
import com.example.aiworkflowback.Flow.Services.FlowConfigService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
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

  @Override
  public Message<FlowConfigEntity> queryFlowConfigInfo(String flowConfigId) {
    try {
      FlowConfigEntity ans = this.flowConfigMapper.selectByFlowConfigId(flowConfigId);
      if (ans == null) {
        return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "没有查询到该流程配置详情数据", null);
      }
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", ans);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }


}
