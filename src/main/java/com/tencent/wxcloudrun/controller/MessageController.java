package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.ReceiveMsgReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MessageController {
    @PostMapping("/wx")
    public void handler(@RequestBody ReceiveMsgReq request) throws Exception {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        Map<String, String> parseXml = MsgParser.parseXml(request);
//        String msgType = parseXml.get("MsgType");
//        String content = parseXml.get("Content");
//        String fromusername = parseXml.get("FromUserName");
//        String tousername = parseXml.get("ToUserName");
        String msgType = request.getMsgType();
        String content = request.getContent();
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        System.out.println(msgType);
        System.out.println(content);
        System.out.println(fromUserName);
        System.out.println(toUserName);
    }
}
