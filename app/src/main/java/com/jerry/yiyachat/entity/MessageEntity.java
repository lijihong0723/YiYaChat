package com.jerry.yiyachat.entity;

public class MessageEntity {

    private String messageInfo;
    private VCardEntity vCardEntity;

    public MessageEntity(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public VCardEntity getvCardEntity() {
        return vCardEntity;
    }

    public void setvCardEntity(VCardEntity vCardEntity) {
        this.vCardEntity = vCardEntity;
    }
}
