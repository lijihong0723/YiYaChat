package com.jerry.yiyachat.entity;

public class MessageEntity {

    private String messageInfo;

    public MessageEntity(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
}
