package com.tencent.wxcloudrun.service;

import java.util.Map;

public interface MsgDispatcherService {
    String processMessage(String msgType, String fromUserName, String content);
}
