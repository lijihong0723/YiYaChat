package com.jerry.yiyachat.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class VCardEntity {

    private String jid;
    private String nickName;
    private String address;
    private String signature;
    private Bitmap photoImage;

    public VCardEntity() {
    }

    public VCardEntity(VCard vCard) {
        jid = vCard.getFrom();
        nickName = vCard.getNickName();
        address = vCard.getAddressFieldHome(
                vCard.getAddressFieldHome("CTRY") + " " + vCard.getAddressFieldHome("LOCALITY"));
        byte[] avatarByteArray = vCard.getAvatar();
        if (avatarByteArray != null) {
            photoImage = BitmapFactory.decodeByteArray(
                    avatarByteArray, 0, avatarByteArray.length);
        }
    }

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
