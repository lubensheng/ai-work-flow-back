package com.example.aiworkflowback.Flow.Services;

import com.example.aiworkflowback.Flow.Modal.Dto.ResponseDto;
import com.example.aiworkflowback.Flow.Modal.Dto.SaveFlowDto;
import com.example.aiworkflowback.Message;

public interface FlowSaveService {
  Message<ResponseDto> saveFlowInfo(SaveFlowDto flowData);
}
