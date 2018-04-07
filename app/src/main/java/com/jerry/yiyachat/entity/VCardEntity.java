package com.jerry.yiyachat.entity;

import android.graphics.Bitmap;

public class VCardEntity {

    private String jid;
    private String nickName;
    private String address;
    private String signature;
    private Bitmap photoImage;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Bitmap getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(Bitmap photoImage) {
        this.photoImage = photoImage;
    }
}
