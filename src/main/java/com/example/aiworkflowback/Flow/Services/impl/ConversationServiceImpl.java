package com.example.aiworkflowback.Flow.Services.impl;

import com.example.aiworkflowback.Flow.Mapper.ConversationMapper;
import com.example.aiworkflowback.Flow.Mapper.FlowInfoMapper;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.CreateConversationReq;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Dto.HistoryConversationInfo;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Entity.ConversationEntity;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Flow.Services.ConversationService;
import com.example.aiworkflowback.Message;
import com.example.aiworkflowback.User.Mapper.UserMapper;
import com.example.aiworkflowback.User.Model.Entity.UserEntity;
import com.example.aiworkflowback.enums.HttpCode;
import com.example.aiworkflowback.utils.ReturnMessageUtils;
import com.example.aiworkflowback.utils.SnowIdUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ConversationServiceImpl implements ConversationService {

  @Resource
  ConversationMapper conversationMapper;

  @Resource
  FlowInfoMapper flowInfoMapper;

  @Resource
  UserMapper userMapper;

  @Override
  public Message<String> createConversationId(CreateConversationReq req) {
    ConversationEntity conversationEntity = new ConversationEntity();
    conversationEntity.setConversationId(SnowIdUtil.nextId());
    conversationEntity.setFlowId(req.getFlowId());
    conversationEntity.setUserName(req.getUserName());
    try {
      FlowEntity flow = flowInfoMapper.selectFlowInfoByFlowId(req.getFlowId());
      UserEntity user = this.userMapper.selectUserByUserName(req.userName);
      if (flow == null) {
        return ReturnMessageUtils.getResponse(HttpCode.NOT_DATA_CODE.getCode(), "success", "没有该流程的信息");
      }
      if (user != null) {
        conversationEntity.setUserId(user.getId().toString());
      }
      conversationMapper.insert(conversationEntity);
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", conversationEntity.getConversationId().toString());
    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), "error", e.getMessage());
    }
  }

  @Override
  public Message<HistoryConversationInfo[]> getHistoryConversationInfByFlowId(String flowId, String userName) {
    try {
      ConversationEntity[] conversationEntities = this.conversationMapper.queryAllByFlowIdAndUserName(flowId, userName);
      ArrayList<HistoryConversationInfo> historyConversationInfos = new ArrayList<>();
      if (conversationEntities == null) {
        return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", new HistoryConversationInfo[0]);
      }
      Arrays.stream(conversationEntities).forEach(item -> {
        HistoryConversationInfo historyConversationInfo = new HistoryConversationInfo();
        historyConversationInfo.setConversationId(item.getConversationId().toString());
        historyConversationInfo.setUserId(item.getUserId());
        historyConversationInfo.setUserName(item.getUserId());
        historyConversationInfo.setUserId(item.getUserId());
        historyConversationInfos.add(historyConversationInfo);
      });
      return ReturnMessageUtils.getResponse(HttpCode.SUCCESS_CODE.getCode(), "success", historyConversationInfos.toArray(new HistoryConversationInfo[0]));

    } catch (Exception e) {
      return ReturnMessageUtils.getResponse(HttpCode.ERROR_CODE.getCode(), e.getMessage(), null);
    }
  }
}
