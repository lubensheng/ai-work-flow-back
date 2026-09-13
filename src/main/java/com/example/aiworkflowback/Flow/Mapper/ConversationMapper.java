package com.example.aiworkflowback.Flow.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Entity.ConversationEntity;
import org.apache.ibatis.annotations.Param;

public interface ConversationMapper extends BaseMapper<ConversationEntity> {
  int insert(ConversationEntity conversationValue);
  ConversationEntity[] queryAllByFlowIdAndUserName(@Param("flowId") String flowId, @Param("userName") String userName);
}
