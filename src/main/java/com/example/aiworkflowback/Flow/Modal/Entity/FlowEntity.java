package com.example.aiworkflowback.Flow.Modal.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.aiworkflowback.commomModal.entity.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class FlowEntity extends commonEntity {
  @TableId(type = IdType.AUTO)
  public Long id;

  public String appName;
  public String appType;
  public String appDesc;
  public String edgeList;
  public String nodeList;
}
