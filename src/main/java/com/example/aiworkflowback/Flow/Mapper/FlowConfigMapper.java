package com.example.aiworkflowback.Flow.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;

public interface FlowConfigMapper extends BaseMapper<FlowConfigEntity> {
  void insertValue(FlowConfigEntity flowConfig);
}
