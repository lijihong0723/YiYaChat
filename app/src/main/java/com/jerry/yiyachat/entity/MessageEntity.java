package com.jerry.yiyachat.entity;

import org.litepal.crud.DataSupport;

public class MessageEntity extends DataSupport {

    private String messageInfo;
    private UserEntity userEntity;

    public MessageEntity(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
