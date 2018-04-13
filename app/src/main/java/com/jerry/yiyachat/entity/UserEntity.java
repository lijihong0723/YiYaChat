package com.jerry.yiyachat.entity;

public class UserEntity {

    private String userName;
    private String jid;
    private VCardEntity vCardEntity;

    public UserEntity(String userName, String jid) {
        this.userName = userName;
        this.jid = jid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public VCardEntity getvCardEntity() {
        return vCardEntity;
    }

    public void setvCardEntity(VCardEntity vCardEntity) {
        this.vCardEntity = vCardEntity;
    }
}
