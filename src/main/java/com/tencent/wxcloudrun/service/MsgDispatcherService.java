package com.tencent.wxcloudrun.service;

import java.util.Map;

public interface MsgDispatcherService {
    String processMessage(Map<String, String> map);

    String processEvent(Map<String, String> map);
}
