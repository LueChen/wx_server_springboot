package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dto.msg.TextMessage;
import com.tencent.wxcloudrun.service.MsgDispatcherService;
import com.tencent.wxcloudrun.utils.MsgGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class MsgDispatcherServiceImpl implements MsgDispatcherService {
    @Override
    public String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID
        if (map.get("MsgType").equals(MsgGenerator.REQ_MESSAGE_TYPE_TEXT)) {
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MsgGenerator.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("这是返回消息");
            return MsgGenerator.textMessageToXml(txtmsg);
        }
        return null;
    }

    @Override
    public String processEvent(Map<String, String> map) {
        return null;
    }
}
