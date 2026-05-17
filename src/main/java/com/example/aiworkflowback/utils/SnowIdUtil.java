package com.example.aiworkflowback.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

public class SnowIdUtil {
  private static long WORKER_ID = 0;
  private static final long DATA_CENTER_ID = 0;
  static {
    try {
      String ip = NetUtil.getLocalhostStr();
      WORKER_ID =  (ip.hashCode() & 0xFFFFFF) % 31;
    } catch (Exception e) {
      WORKER_ID = 1;
    }
  }

  private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(WORKER_ID, DATA_CENTER_ID);

  public static long nextId() {
    return SNOWFLAKE.nextId();
  }

}
