package com.jerry.yiyachat.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.List;

public class UserEntity extends DataSupport {

    @Column(unique = true)
    private String jid;

    private String userName;
    private String address;
    private byte[] photo;
    private List<MessageEntity> messages;

    @Column(ignore = true)
    private Bitmap photoBitmap;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public Bitmap getPhotoBitmap() {
        if (photoBitmap == null) {
            photoBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        }
        return photoBitmap;
    }
}
