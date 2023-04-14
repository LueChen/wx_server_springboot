package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class ReceiveMsgReq {
    private String URL;
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String content;
    private long MsgId;
}
