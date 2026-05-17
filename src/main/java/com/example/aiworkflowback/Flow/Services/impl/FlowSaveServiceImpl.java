package com.example.aiworkflowback.Flow.Services.impl;

import com.alibaba.fastjson2.JSON;
import com.example.aiworkflowback.Flow.Mapper.FlowInfoMapper;
import com.example.aiworkflowback.Flow.Modal.Dto.QueryFlow;
import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.FlowSaveService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FlowSaveServiceImpl implements FlowSaveService {

  @Resource
  FlowInfoMapper flowInfoMapper;

  @Override
  public Message<ResponseDto> saveFlowInfo(SaveFlowDto flowData) {
    ResponseDto r = new ResponseDto();
    r.appId = flowData.appName;
    FlowEntity f = new FlowEntity();
    f.appName = flowData.appName;
    f.appDesc = flowData.appDesc;
    f.appType = flowData.appType;
    f.nodeList = JSON.toJSONString(flowData.nodeList);
    f.edgeList = JSON.toJSONString(flowData.edgeList);
    try {
      FlowEntity f1 = flowInfoMapper.selectFlowInfoByFlowName(flowData.appName);
      if (f1 != null) {
        return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "已经存在这个app", r);
      }
      flowInfoMapper.insertFlowData(f);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "新增成功", r);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), r);
    }
  }

  @Override
  public Message<FlowEntity> queryFlowInfo(QueryFlow queryInfo) {
    queryInfo.offset =( queryInfo.pageIndex - 1) * queryInfo.pageSize;
    try {
      FlowEntity r = flowInfoMapper.selectFlowInfoByPagination(queryInfo);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "新增成功", r);

    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }
}
