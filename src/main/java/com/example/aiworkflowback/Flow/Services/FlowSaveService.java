package com.example.aiworkflowback.Flow.Services;

import com.example.aiworkflowback.Flow.Modal.Dto.QueryFlow;
import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.ReturnFlowInfo;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Flow.Modal.Entity.FlowEntity;
import com.example.aiworkflowback.Message;

public interface FlowSaveService {
  Message<ResponseDto> saveFlowInfo(SaveFlowDto flowData);
  Message<ReturnFlowInfo> queryFlowInfo(QueryFlow queryInfo);
  FlowEntity queryFlowInfoById(String flowId);
  int selectFlowTotal(String userName);
}
