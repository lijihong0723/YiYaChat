package com.jerry.yiyachat.entity;

public class UserEntity {

    private String userName;

    public UserEntity(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
