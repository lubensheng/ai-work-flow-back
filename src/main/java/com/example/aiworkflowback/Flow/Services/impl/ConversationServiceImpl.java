package com.example.aiworkflowback.Flow.Services.impl;

import com.example.aiworkflowback.Flow.Mapper.ConversationMapper;
import com.example.aiworkflowback.Flow.Mapper.FlowInfoMapper;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.CreateConversationReq;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Entity.ConversationEntity;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.ConversationService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import com.example.aiworkflowback.utils.SnowIdUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl implements ConversationService {

  @Resource
  ConversationMapper conversationMapper;

  @Resource
  FlowInfoMapper flowInfoMapper;

  @Override
  public Message<String> createConversationId(CreateConversationReq req) {
    ConversationEntity conversationEntity = new ConversationEntity();
    conversationEntity.setConversationId(SnowIdUtil.nextId());
    conversationEntity.setFlowId(req.getFlowId());
    conversationEntity.setUserName(req.getUserName());
    conversationEntity.setUserId(req.getUserId());
    try {
      FlowEntity flow = flowInfoMapper.selectFlowInfoByFlowId(req.getFlowId());
      if (flow == null) {
        return ReturnMessageUtils.getResponse(HttpCode.NOT_DATA_CODE.getCode(), "success", "没有该流程的信息");
      }
      conversationMapper.insert(conversationEntity);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", conversationEntity.getConversationId().toString());
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "error", e.getMessage());
    }
  }
}
