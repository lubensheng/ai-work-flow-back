package com.example.aiworkflowback.Flow.Services.impl;

import com.alibaba.fastjson2.JSON;
import com.example.aiworkflowback.Flow.Mapper.FlowInfoMapper;
import com.example.aiworkflowback.Flow.Modal.Dto.QueryFlow;
import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowConfigEntity;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.FlowSaveService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import com.example.aiworkflowback.User.Service.impl.UserServiceImpl;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import com.example.aiworkflowback.utils.SnowIdUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FlowSaveServiceImpl implements FlowSaveService {

  @Resource
  FlowInfoMapper flowInfoMapper;

  @Resource
  FlowConfigServiceImpl flowConfigService;

  @Resource
  UserServiceImpl userService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Message<ResponseDto> saveFlowInfo(SaveFlowDto flowData) {
    ResponseDto r = new ResponseDto();
    r.appId = flowData.appName;
    LocalDateTime currentTime = LocalDateTime.now();
    FlowEntity flowInfo = new FlowEntity();


    FlowConfigEntity flowConfig = new FlowConfigEntity();
    flowConfig.setFlowConfigId(SnowIdUtil.nextId());
    flowConfig.setNodeList(JSON.toJSONString(flowData.getEdgeList()));
    flowConfig.setEdgeList(JSON.toJSONString(flowData.getNodeList()));
    flowConfig.setCreateTime(currentTime);
    flowConfig.setUpdateTime(currentTime);

    flowInfo.setFlowConfigId(flowConfig.getFlowConfigId());
    flowInfo.setAppName(flowData.getAppName());
    flowInfo.setFlowStatus(flowData.getFlowStatus());
    flowInfo.setCreateTime(currentTime);
    flowInfo.setUpdateTime(currentTime);
    flowInfo.setAppDesc(flowData.getAppDesc());
    flowInfo.setAppType(flowData.getAppType());
    flowInfo.setUserName(flowData.getUserName());

    UserEntity userInfo = this.userService.selectUserInfoByUserName(flowData.getUserName());

    if (userInfo == null) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "未知用户", null);
    }
    flowInfo.setUserId(userInfo.getId());
    try {
      flowConfigService.saveFlowConfig(flowConfig);
      this.flowInfoMapper.insertFlowData(flowInfo);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", null);
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
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

  @Override
  public FlowEntity queryFlowInfoById(String flowId) {
    try {
      return this.flowInfoMapper.selectFlowInfoByFlowId(flowId);
    } catch (Exception e) {
      return null;
    }
  }
}
