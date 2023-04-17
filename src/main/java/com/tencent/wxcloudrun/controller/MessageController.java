package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.MsgDispatcherService;
import com.tencent.wxcloudrun.utils.MsgGenerator;
import com.tencent.wxcloudrun.utils.MsgParser;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class MessageController {
    final Logger logger = LoggerFactory.getLogger(CounterController.class);

    @Resource
    MsgDispatcherService msgDispatcherService;

    @PostMapping("/wx")
    public String handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = MsgParser.parseXml(request);

        String action = map.getOrDefault("action", "default");
        String msgType = map.getOrDefault("MsgType", "noType");
        String content = map.getOrDefault("Content", "noContent");
        String fromUserName = map.getOrDefault("FromUserName", "");
//        String toUserName = map.get("ToUserName");

        // 配置云托管消息推送时需要使用
        if (action.equals("CheckContainerPath")) {
            logger.info("This is a checking req.");
            return "success";
        }

        logger.info("Receive msg and process it. Msg: " + content + " from: " + fromUserName);
        return msgDispatcherService.processMessage(msgType, fromUserName, content);
    }
}
