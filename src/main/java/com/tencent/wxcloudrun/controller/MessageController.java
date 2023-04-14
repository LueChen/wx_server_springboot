package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.ReceiveMsgReq;
import com.tencent.wxcloudrun.utils.MsgParser;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class MessageController {
    final Logger logger = LoggerFactory.getLogger(CounterController.class);

    @PostMapping("/wx")
    public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
        Map<String, String> parseXml = MsgParser.parseXml(request);
        String msgType = parseXml.get("MsgType");
        String content = parseXml.get("Content");
        String fromUserName = parseXml.get("FromUserName");
        String toUserName = parseXml.get("ToUserName");
        logger.info(request.toString());

//        String msgType = request.getMsgType();
//        String content = request.getContent();
//        String fromUserName = request.getFromUserName();
//        String toUserName = request.getToUserName();
        System.out.println(msgType);
        System.out.println(content);
        System.out.println(fromUserName);
        System.out.println(toUserName);
    }
}
