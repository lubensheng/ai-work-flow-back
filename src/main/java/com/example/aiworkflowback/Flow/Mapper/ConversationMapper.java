package com.example.aiworkflowback.Flow.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiworkflowback.Flow.Modal.ConversationModal.Entity.ConversationEntity;

public interface ConversationMapper extends BaseMapper<ConversationEntity> {
  int insert(ConversationEntity conversationValue);
}
