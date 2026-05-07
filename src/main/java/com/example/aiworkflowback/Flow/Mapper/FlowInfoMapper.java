package com.example.aiworkflowback.Flow.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import org.apache.ibatis.annotations.Param;

public interface FlowInfoMapper extends BaseMapper<FlowEntity> {
  void insertFlowData(FlowEntity flowIfo);
  FlowEntity selectFlowInfoByFlowName(@Param("flowName") String flowName);
}
