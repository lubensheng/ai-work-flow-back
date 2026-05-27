package com.example.aiworkflowback.Flow.Modal.Dto;

import com.example.aiworkflowback.commomModal.dto.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryFlow extends Pagination {
  public int offset;
  public String userName;
}
