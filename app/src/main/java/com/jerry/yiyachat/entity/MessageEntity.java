package com.jerry.yiyachat.entity;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class MessageEntity extends DataSupport {

    private String messageInfo;
    private boolean isAlreadyRead;
    private Date date;
    private UserEntity userEntity;

    public MessageEntity() {
    }

    public MessageEntity(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public MessageEntity(
            String messageInfo, boolean isAlreadyRead, Date date, UserEntity userEntity) {
        this.messageInfo = messageInfo;
        this.isAlreadyRead = isAlreadyRead;
        this.date = date;
        this.userEntity = userEntity;
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

    public boolean isAlreadyRead() {
        return isAlreadyRead;
    }

    public void setAlreadyRead(boolean alreadyRead) {
        isAlreadyRead = alreadyRead;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
