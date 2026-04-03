package com.example.aiworkflowback.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataTimeMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    // 严格填充：有字段就填，没有就跳过，不会报错！
    strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
    strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
  }
}
