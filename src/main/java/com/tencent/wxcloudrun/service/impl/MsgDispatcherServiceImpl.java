package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dto.msg.TextMessage;
import com.tencent.wxcloudrun.service.MsgDispatcherService;
import com.tencent.wxcloudrun.utils.MsgGenerator;
import com.tencent.wxcloudrun.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MsgDispatcherServiceImpl implements MsgDispatcherService {
    private final String DEFAULT_REPLY = "Default Reply, Nothing has happened.";
    private final String QUESTION_PREFIX = "Q:";
    private final String ANSWER_PREFIX = "A:";
    final Logger logger = LoggerFactory.getLogger(MsgDispatcherService.class);

    private String generateContent(String content) {
        if (content.startsWith(QUESTION_PREFIX)) {
            // TODO: call chatgpt interface from US server
            return ANSWER_PREFIX + " " + content;
        }

        return DEFAULT_REPLY;
    }

    @Override
    public String processMessage(String msgType, String fromUserName, String content) {
        // TODO 消息存库，你保存好消息事件戳，如果当前消息与上一条消息在15分钟之内，调用gpt的时候，将其作为上下文传入gpt，或者在gpt server考虑解决

        if (msgType.equals(MsgGenerator.REQ_MESSAGE_TYPE_EVENT)) {
            // 事件类型，先不处理
            logger.info("This is a event msg: " + content);
            return Constants.SUCCESS;
        } else if (msgType.equals(MsgGenerator.REQ_MESSAGE_TYPE_TEXT)) {
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(fromUserName);
            txtmsg.setFromUserName(Constants.GZH_ID);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MsgGenerator.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent(generateContent(content));

            String xmlMsg = MsgGenerator.textMessageToXml(txtmsg);
            logger.info("Response Msg: " + xmlMsg);
            return xmlMsg;
        } else {
            logger.info("This is a event msg: " + content);
            return "Unsupported msg type.";
        }
    }
}
