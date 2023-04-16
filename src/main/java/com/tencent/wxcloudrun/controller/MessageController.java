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

        String msgType = map.get("MsgType");
        String content = map.get("Content");
        String fromUserName = map.get("FromUserName");
//        String toUserName = map.get("ToUserName");

        if (MsgGenerator.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            return msgDispatcherService.processEvent(map);
        } else {
            logger.info("Receive msg and process it. Msg: " + content + " from: " + fromUserName);
            return msgDispatcherService.processMessage(map);
        }
    }
}
